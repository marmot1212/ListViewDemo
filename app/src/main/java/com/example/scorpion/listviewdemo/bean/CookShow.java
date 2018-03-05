package com.example.scorpion.listviewdemo.bean;

/**
 * Created by scorpion on 2018/3/5.
 */

public class CookShow {
    private int picId;
    private int avatarId;
    private String tvUsername;
    private String tvTitle;
    private int likeNum;
    private int commentNum;

    public CookShow() {
    }

    public CookShow(int picId, int avatarId, String tvUsername, String tvTitle, int likeNum, int commentNum) {
        this.picId = picId;
        this.avatarId = avatarId;
        this.tvUsername = tvUsername;
        this.tvTitle = tvTitle;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public String getTvUsername() {
        return tvUsername;
    }

    public void setTvUsername(String tvUsername) {
        this.tvUsername = tvUsername;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
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
