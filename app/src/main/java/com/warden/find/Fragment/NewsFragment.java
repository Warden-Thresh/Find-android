package com.warden.find.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.warden.find.R;
import com.warden.find.adapter.ChannelAdapter;
import com.warden.find.adapter.ItemDragHelperCallback;
import com.warden.find.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/8/17.
 */
public class NewsFragment extends Fragment {

    private String[] tabs = {"头条", "体育", "娱乐", "财经", "科技", "电影", "汽车", "笑话", "游戏", "足球"
            , "时尚", "情感", "精选", "电台"
            , "NBA", "数码", "移动", "彩票"
            , "教育", "论坛", "旅游", "手机"
            , "博客", "社会", "家居", "暴雪游戏"
            , "亲子", "CBA", "消息", "军事"};

    @BindView(R.id.btn_tab_select)
    View btn_tab_select;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_news_main)
    ViewPager vp;
    private PopupWindow pop;
    private boolean isPopupWindowShowing = false;
    private List<String> myChannels;
    private List<String> alterChannels;
    private ChannelAdapter adapter;
    private NewsAdapter vpAdapter;
    private RecyclerView rv_pop;


    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);

        SharedPreferences sp = getActivity().getSharedPreferences("tabs", Context.MODE_PRIVATE);
        String tabString = sp.getString("tabs", tabsToString());
        tabs = stringToTabs(tabString);
        String ohterString = sp.getString("others", "");
        String[] others = stringToTabs(ohterString);

        myChannels = (tabs == null ? new ArrayList<>() : Arrays.asList(tabs));

        alterChannels = (others == null ? new ArrayList<>() : Arrays.asList(others));

        // 实现tab效果

        vpAdapter = new NewsAdapter(getChildFragmentManager(), myChannels);
        vp.setAdapter(vpAdapter);
        vp.setOffscreenPageLimit(1);
        tab.setupWithViewPager(vp);
        if (myChannels.size() < 5)
            tab.setTabMode(TabLayout.MODE_FIXED);
        else
            tab.setTabMode(TabLayout.MODE_SCROLLABLE);

        btn_tab_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popupwindow_selecttab, null);

                rv_pop = (RecyclerView) popView.findViewById(R.id.rv_popup);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
                rv_pop.setLayoutManager(manager);

                ItemDragHelperCallback cb = new ItemDragHelperCallback();
                ItemTouchHelper helper = new ItemTouchHelper(cb);
                helper.attachToRecyclerView(rv_pop);

                Button btn_popup = (Button) popView.findViewById(R.id.btn_popup);
                TextView tv_popup = (TextView) popView.findViewById(R.id.tv_popup);
                pop = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

                adapter = new ChannelAdapter(getActivity(), helper, myChannels, alterChannels, btn_popup, tv_popup);
                btn_popup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!adapter.getIsEditMode()) {
                            refreshUI();
                        } else {
                            adapter.cancelEditMode(rv_pop);
                        }
                    }
                });
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int viewType = adapter.getItemViewType(position);
                        return viewType == ChannelAdapter.TYPE_OTHER_CHANNEL_HEADER ? 4 : 1;
                    }
                });

                rv_pop.setAdapter(adapter);
                adapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        refreshUI();
                        int currPos = tab.getSelectedTabPosition();
                        if (currPos != position) {
                            tab.setScrollPosition(currPos, position - currPos, true);
                            vp.setCurrentItem(position);
                        }
                    }
                });

                pop.setBackgroundDrawable(null);
                pop.setFocusable(false);
                pop.showAsDropDown(btn_tab_select, 0, -btn_tab_select.getHeight());
                isPopupWindowShowing = true;
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public boolean getIsPopupWindowShowing() {
        return isPopupWindowShowing;
    }

    public void onBackPressed() {
        if (pop != null) {
            if (pop.isShowing()) {
                if (adapter.getIsEditMode()) {
                    adapter.cancelEditMode(rv_pop);
                    return;
                }
                refreshUI();
            }
        }
    }

    private void refreshUI() {
        myChannels = adapter.getmMyChannelItems();
        alterChannels = adapter.getmOtherChannelItems();
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("tabs", Context.MODE_PRIVATE).edit();
        editor.putString("tabs", listToString(myChannels));
        editor.putString("others", listToString(alterChannels));
        editor.apply();
        vpAdapter.setTabs(myChannels);
        if (myChannels.size() < 5)
            tab.setTabMode(TabLayout.MODE_FIXED);
        else
            tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        vpAdapter.notifyDataSetChanged();
        pop.dismiss();
        isPopupWindowShowing = false;
    }

    private String listToString(List<String> list) {
        String str = "";
        for (String s : list)
            str += s + "-";
        return str;
    }

    private String tabsToString() {
        String s = "";
        for (String str : tabs)
            s += str + "-";
        return s;
    }

    private String[] stringToTabs(String tabString) {
        if (!tabString.equals(""))
            return tabString.split("-");
        else
            return null;
    }
}


