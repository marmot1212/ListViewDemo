package com.example.scorpion.listviewdemo.model.bean;

/**
 * Created by scorpion on 2018/3/18.
 */

public class Banner {
    private String title;
    private String url;

    public Banner() {
    }

    public Banner(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
