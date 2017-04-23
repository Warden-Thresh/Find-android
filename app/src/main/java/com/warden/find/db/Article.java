package com.warden.find.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Warden on 2017/4/21.
 */

public class Article extends DataSupport{
    private int id;
    private String title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
