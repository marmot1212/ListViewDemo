package com.example.scorpion.listviewdemo.bean;

/**
 * Created by scorpion on 2018/3/7.
 */

public class Boutique {
    private int picId;
    private String title;
    private String content;
    private int likeNum;
    private int commentNum;

    @Override
    public String toString() {
        return "Boutique{" +
                "picId=" + picId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likeNum=" + likeNum +
                ", commentNum=" + commentNum +
                '}';
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
