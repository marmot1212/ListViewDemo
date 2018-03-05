package com.example.scorpion.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.scorpion.listviewdemo.adapter.HomeAdapter;
import com.example.scorpion.listviewdemo.bean.CookShow;
import com.example.scorpion.listviewdemo.bean.HeadBanner;
import com.example.scorpion.listviewdemo.holder.HeadBannerHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private ListView mListView;
    private int[] imgArr04;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initData();
    }

    private void initData() {
        imgArr04 = new int[]{R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4};
        Log.i("main", TAG+", imgArr04 = "+ Arrays.toString(imgArr04));
        // TODO : 数据ArrayList —— 适配器HomeAdapter —— 控件封装实体类ViewHolder——控件复用

        addHeadBannerToListView();
    }

    private void addHeadBannerToListView() {
        // 数据
        List<HeadBanner> list = new ArrayList<>();
        String[] titleArr = new String[]{"第1个轮播图", "第二个轮播图", "第3个轮播图", "第四个轮播图"};
        for(int i=0; i<imgArr04.length; i++) {
            HeadBanner banner = new HeadBanner(imgArr04[i],titleArr[i]);
            list.add(banner);
        }


        // holder 实例化
        HeadBannerHolder holder = new HeadBannerHolder(this, imgArr04, titleArr);
        // 获得view
        // 加入到mListView
        mListView.addHeaderView(holder.getConvertView());
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new HomeAdapter();
        mListView.setAdapter(mAdapter);
    }
}
