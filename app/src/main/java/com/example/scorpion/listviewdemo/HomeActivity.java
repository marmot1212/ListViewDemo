package com.example.scorpion.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.scorpion.listviewdemo.adapter.HomeAdapter;
import com.example.scorpion.listviewdemo.bean.HomeBean;
import com.example.scorpion.listviewdemo.bean.ItemType;
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
    private List<HomeBean> mList;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initData();
        initView();

        setListener();
    }

    private void setListener() {

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.i("main", TAG+", onScrollStateChanged, scrollState = "+scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i("main", TAG+", onScroll, firstVisibleItem = "+firstVisibleItem+", visibleItemCount ="
                        +visibleItemCount+", totalItemCount"+totalItemCount);
                if (firstVisibleItem >= 1) {
                    mRelativeLayout.setVisibility(View.VISIBLE);
                } else {
                    mRelativeLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initData() {
        imgArr04 = new int[]{R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4};
        mList = new ArrayList<>();

        // SIGN_MALL 签到和商城
        HomeBean signMallBean = new HomeBean();
        signMallBean.setItemType(ItemType.SIGN_MALL);
        mList.add(signMallBean);

        // tag01：专辑推荐
        HomeBean tagBean = new HomeBean();
        tagBean.setItemType(ItemType.TAG);
        tagBean.setTagTitle("专辑推荐");
        mList.add(tagBean);

        // tag02 精选菜谱
        HomeBean tagBean03 = new HomeBean();
        tagBean03.setItemType(ItemType.TAG);
        tagBean03.setTagTitle("精选菜谱");
        mList.add(tagBean03);

        // tag03 美食达人
        HomeBean tagBean04 = new HomeBean();
        tagBean04.setItemType(ItemType.TAG);
        tagBean04.setTagTitle("美食达人");
        mList.add(tagBean04);

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
        mRelativeLayout = (RelativeLayout) findViewById(R.id.search_bar);
        mAdapter = new HomeAdapter(this, mList);
        mListView.setAdapter(mAdapter);
    }
}
