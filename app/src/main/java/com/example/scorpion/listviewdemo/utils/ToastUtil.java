package com.example.scorpion.listviewdemo.utils;

import android.widget.Toast;

import com.example.scorpion.listviewdemo.MyApplication;

/**
 * Created by scorpion on 2018/3/7.
 */

public class ToastUtil {
    public ToastUtil() {
    }

    public static void showTest(CharSequence text) {
        if (text.length() <= 10) {
            Toast.makeText(MyApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MyApplication.getInstance(), text, Toast.LENGTH_LONG).show();
        }
    }
}
