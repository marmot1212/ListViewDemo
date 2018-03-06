package com.example.scorpion.listviewdemo.bean;

/**
 * Created by scorpion on 2018/3/6.
 */

public enum  ItemType {
    SIGN_MALL(0), TAG(1), BOUTIQUE(2), AD(3), RECIPE(4), COOK_SHOW(5), GOURMET(6);
    int value;

    ItemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
