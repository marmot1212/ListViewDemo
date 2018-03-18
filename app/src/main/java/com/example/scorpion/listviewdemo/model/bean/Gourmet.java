package com.example.scorpion.listviewdemo.model.bean;

/**
 * Created by scorpion on 2018/3/7.
 */

public class Gourmet {
    private String url;
    private String title;

    public Gourmet() {
    }

    public Gourmet(String title, String url) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Gourmet{" +
                "url=" + url +
                ", title='" + title + '\'' +
                '}';
    }
}
