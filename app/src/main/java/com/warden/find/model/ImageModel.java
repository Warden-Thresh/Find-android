package com.warden.find.model;

import org.litepal.crud.DataSupport;

/**
 * Created by WangChenchen on 2016/8/19.
 */
public class ImageModel extends DataSupport {
    public String img_id;
    public String imgsrc;
    public String article_id;
    private NewsModel newsModel;

    public NewsModel getNewsModel() {
        return newsModel;
    }

    public void setNewsModel(NewsModel newsModel) {
        this.newsModel = newsModel;
    }
}
