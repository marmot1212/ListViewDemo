package com.example.scorpion.listviewdemo.bean;

/**
 * Created by scorpion on 2018/3/7.
 */

public class Gourmet {
    private int picId;
    private String title;

    public Gourmet() {
    }

    public Gourmet(int picId, String title) {
        this.picId = picId;
        this.title = title;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
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
                "picId=" + picId +
                ", title='" + title + '\'' +
                '}';
    }
}
