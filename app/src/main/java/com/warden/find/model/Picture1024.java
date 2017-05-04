package com.warden.find.model;

import org.litepal.crud.DataSupport;

/**
 * Created by Warden on 2017/4/22.
 */

public class Picture1024 extends DataSupport{
    private int id;
    private String pictureid;
    private String Url;
    private String articleid;

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureid() {
        return pictureid;
    }

    public void setPictureid(String pictureid) {
        this.pictureid = pictureid;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
