package com.example.scorpion.listviewdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by scorpion on 2018/3/7.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance(){
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }
}
