package com.warden.find.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.warden.find.R;
import com.warden.find.model.ImageModel;
import com.warden.find.model.NewsModel;
import com.warden.find.spider.SpiderPic;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 *
 */
public class NewsDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ON_SUCCESS = 0;
    private final int ON_FAILURE = 1;
    public static final int LOAD_MORE = 4;
    public static final int LOADING = 5;
    public static final int NO_MORE_DATA = 6;
    public final int HEADER = 0;
    public final int NORMAL = 1;
    public final int MULTIIMAGE = 2;
    public final int FOOTER = 3;
    private int load_status = LOAD_MORE;
    private Context mContext;
    // 将RollPagerView作为HeaderView添加给RecyclerView
    private List<NewsModel> mList;
    private List<ImageModel> imageModelList;
    private View headerView;
    private OnItemClickListener listener;

    public NewsDetailAdapter(Context context, List<NewsModel> list) {
        mContext = context;
        mList = list;
    }

    // headerView的setter
    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView == null) {
            if (position + 1 == getItemCount()) return FOOTER;
            NewsModel currNM = mList.get(position);

            if (currNM.type=="1024")
                return 4;
            if (currNM.imgextra == null || currNM.imgextra.size() == 0)
                return NORMAL;

            return MULTIIMAGE;
        }
        if (position == 0) return HEADER;
        if (position + 1 == getItemCount()) return FOOTER;
        NewsModel currNM = mList.get(position - 1);
        if (currNM.imgextra == null || currNM.imgextra.size() == 0)
            return NORMAL;
        if (currNM.type=="1024")
            return 4;
        return MULTIIMAGE;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null && viewType == HEADER)
            return new ViewHolderNormal(headerView);
        if (viewType == NORMAL) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_detail_rv, parent, false);
            return new ViewHolderNormal(view);
        }

        if (viewType == FOOTER) {
            TextView view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.layout_footer, parent, false);
            return new ViewHolderFooter(view);
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image_news_detail_rv, parent, false);
        return new ViewHolderMultiImage(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (getItemViewType(position) == HEADER)
            return;

        if (getItemViewType(position) == FOOTER) {
            ViewHolderFooter footerHolder = (ViewHolderFooter) holder;
            switch (load_status) {
                case LOAD_MORE:
                    footerHolder.text.setText("上拉加载更多...");
                    break;
                case LOADING:
                    footerHolder.text.setText("正在加载更多数据...");
                    break;
                case NO_MORE_DATA:
                    footerHolder.text.setText("没有更多数据");
                    break;
            }
            return;
        }


        final int pos = getRealPosition(holder);
        NewsModel currNM = mList.get(pos);
        Handler Handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                ViewHolderMultiImage imageHolder_1024 = (ViewHolderMultiImage) holder;
                switch (msg.what) {
                    case ON_SUCCESS:
                        Log.d("Pic:",imageModelList.toString());
                        Glide.with(mContext).load(imageModelList.get(0).imgsrc).crossFade().into(imageHolder_1024.iv_item_news_detail_rv);
                        Glide.with(mContext).load(imageModelList.get(1).imgsrc).crossFade().into(imageHolder_1024.iv_center);
                        Glide.with(mContext).load(imageModelList.get(2).imgsrc).crossFade().into(imageHolder_1024.iv_right);
                        break;
                    case ON_FAILURE:

                        break;
                    default:
                        super.handleMessage(msg);
                        break;
                }
            }
        };
        switch (getItemViewType(position)) {
            case NORMAL:
                ViewHolderNormal normalHolder = (ViewHolderNormal) holder;
                normalHolder.tv_title.setText(currNM.title);
                normalHolder.tv_author.setText(currNM.source);

                if ("KmustJob".equals(currNM.type)){
                    normalHolder.iv_item_news_detail_rv.setVisibility(View.GONE);
                    normalHolder.tv_zan.setText(currNM.time);
                }else {

                    Glide.with(mContext).load(currNM.imgsrc).crossFade().into(normalHolder.iv_item_news_detail_rv);
                }



                break;
            case MULTIIMAGE:
                ViewHolderMultiImage imageHolder = (ViewHolderMultiImage) holder;
                imageHolder.tv_title.setText(currNM.title);
                imageHolder.tv_author.setText(currNM.source);
                imageHolder.tv_zan.setText(currNM.replyCount + "跟帖");
                Glide.with(mContext).load(currNM.imgsrc).crossFade().into(imageHolder.iv_item_news_detail_rv);

                if (currNM.imgextra != null && currNM.imgextra.size() > 1) {
                    Glide.with(mContext).load(currNM.imgextra.get(0).imgsrc).crossFade().into(imageHolder.iv_center);
                    Log.d("Pic:",currNM.imgextra.get(0).imgsrc);
                    Glide.with(mContext).load(currNM.imgextra.get(1).imgsrc).crossFade().into(imageHolder.iv_right);
                }
                break;
            case 4:
                ViewHolderMultiImage imageHolder_1024 = (ViewHolderMultiImage) holder;
                imageHolder_1024.tv_title.setText(currNM.title);
                //imageHolder_1024.tv_author.setText(currNM.source);
               // imageHolder_1024.tv_zan.setText(currNM.replyCount + "跟帖");
                //Glide.with(mContext).load(currNM.imgsrc).crossFade().into(imageHolder_1024.iv_item_news_detail_rv);
               // List<NewsModel> imageModelList = DataSupport.where("type = ?","1024").find(NewsModel.class);
                List<ImageModel> imageModels = DataSupport.where("article_id = ?",currNM.url).find(ImageModel.class);
                if (imageModels.size() >= 3){
                    Log.d("DATA:",imageModels.toString());
                    Glide.with(mContext).load(imageModels.get(0).imgsrc).crossFade().into(imageHolder_1024.iv_item_news_detail_rv);
                    Glide.with(mContext).load(imageModels.get(1).imgsrc).crossFade().into(imageHolder_1024.iv_center);
                    Glide.with(mContext).load(imageModels.get(2).imgsrc).crossFade().into(imageHolder_1024.iv_right);

                }else {
                    Glide.with(mContext).load(R.mipmap.loading).into(imageHolder_1024.iv_center);
                    Glide.with(mContext).load(R.mipmap.loading).into(imageHolder_1024.iv_right);
                    Glide.with(mContext).load(R.mipmap.loading).into(imageHolder_1024.iv_item_news_detail_rv);
                    new Thread() {
                        @Override
                        public void run() {
                            imageModelList = SpiderPic.doGet(currNM.url);
                            if (imageModelList != null){
                                Handler.sendEmptyMessage(ON_SUCCESS);
                            }else {
                                Handler.sendEmptyMessage(ON_FAILURE);
                            }

                        }
                    }.start();
                }



                break;

        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onItemClick(pos, mList.get(pos));
                }
            }
        });
    }



    // 用新数据替换mList内容
    public void changeList(List<NewsModel> newData) {
        mList.clear();
        mList.addAll(newData);
        notifyDataSetChanged();
    }

    public List<NewsModel> getmList() {
        return mList;
    }

    // 上拉加载更多数据时调用
    public void addAll(List<NewsModel> newData) {
        mList.addAll(newData);
        notifyDataSetChanged();
    }

    // 改变脚布局的文字
    public void changeLoadStatus(int status) {
        load_status = status;
        notifyDataSetChanged();
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return headerView == null ? mList.size() + 1 : mList.size() + 2;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Object object);
    }

    public class ViewHolderNormal extends RecyclerView.ViewHolder {
        private ImageView iv_item_news_detail_rv;
        private TextView tv_title;
        private TextView tv_author;
        private TextView tv_zan;

        public ViewHolderNormal(View itemView) {
            super(itemView);
            if (itemView == headerView) return;
            iv_item_news_detail_rv = (ImageView) itemView.findViewById(R.id.iv_item_news_detail_rv);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title_news_detail);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author_news_detail);
            tv_zan = (TextView) itemView.findViewById(R.id.tv_zan_news_detail);
        }
    }

    public class ViewHolderMultiImage extends RecyclerView.ViewHolder {

        private ImageView iv_center;
        private ImageView iv_right;
        private ImageView iv_item_news_detail_rv;
        private TextView tv_title;
        private TextView tv_author;
        private TextView tv_zan;

        public ViewHolderMultiImage(View itemView) {
            super(itemView);

            iv_item_news_detail_rv = (ImageView) itemView.findViewById(R.id.iv_left_item_image);
            iv_center = (ImageView) itemView.findViewById(R.id.iv_center_item_image);
            iv_right = (ImageView) itemView.findViewById(R.id.iv_right_item_image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title_item_image);
            tv_author = (TextView) itemView.findViewById(R.id.tv_source_item_image);
            tv_zan = (TextView) itemView.findViewById(R.id.tv_reply_item_image);
        }
    }

    public class ViewHolderFooter extends RecyclerView.ViewHolder {
        private TextView text;

        public ViewHolderFooter(View itemView) {
            super(itemView);
            text = (TextView) itemView;
        }
    }
}

