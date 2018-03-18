package com.example.scorpion.listviewdemo.model.bean;

/**
 * Created by scorpion on 2018/3/7.
 */

public class Recipe {
    private int picId;
    private String title;
    private String introduce;
    private int likeNum;
    private int commentNum;

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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "picId=" + picId +
                ", title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", likeNum=" + likeNum +
                ", commentNum=" + commentNum +
                '}';
    }
}
