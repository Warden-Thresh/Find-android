package com.warden.find.model;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by WangChenchen on 2016/8/19.
 */
public class NewsModel extends DataSupport  {

    // 共同
    public int replyCount;
    public String time;
    public String docid;
    public String type;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String title;
    public String source;
    public String imgsrc;
    // 顶部
    public List<Ads> ads;
    public String photosetID;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 多图
    public List<ImageModel> imgextra;

    // 普通
    public String url;

    public List<ImageModel> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImageModel> imgextra) {
        this.imgextra = imgextra;
    }

    public String subtitle;


}
