package com.example.scorpion.listviewdemo.bean;

/**
 * Created by scorpion on 2018/3/5.
 */

public class HeadBanner {
    private int picId;
    private String title;

    public HeadBanner(int picId, String title) {
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
}
