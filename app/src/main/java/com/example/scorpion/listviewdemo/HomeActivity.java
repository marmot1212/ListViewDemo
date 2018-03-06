package com.example.scorpion.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.scorpion.listviewdemo.adapter.HomeAdapter;
import com.example.scorpion.listviewdemo.holder.HeadBannerHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    @BindView(R.id.listView)
    ListView mListView;
    private int[] imgArr04;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        imgArr04 = new int[]{R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4};
        Log.i("main", TAG + ", imgArr04 = " + Arrays.toString(imgArr04));
        // TODO : 数据ArrayList —— 适配器HomeAdapter —— 控件封装实体类ViewHolder——控件复用

        addHeadBannerToListView();
        addSearchBarToListView();
    }

    private void addSearchBarToListView() {
        View view = View.inflate(this, R.layout.layout_search_bar, null);
        mListView.addHeaderView(view);
    }

    private void addHeadBannerToListView() {
        // 数据
        String[] titleArr = new String[]{"第1个轮播图", "第二个轮播图", "第3个轮播图", "第四个轮播图"};

        // holder 实例化
        HeadBannerHolder holder = new HeadBannerHolder(this, imgArr04, titleArr);
        // 获得view
        // 加入到mListView
        mListView.addHeaderView(holder.getConvertView());
    }

    private void initView() {
        mAdapter = new HomeAdapter();
        mListView.setAdapter(mAdapter);
    }
}
