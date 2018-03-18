package com.example.scorpion.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.scorpion.listviewdemo.contro.adapter.HomeAdapter;
import com.example.scorpion.listviewdemo.contro.holder.HeadBannerHolder;
import com.example.scorpion.listviewdemo.model.bean.Banner;
import com.example.scorpion.listviewdemo.model.bean.Boutique;
import com.example.scorpion.listviewdemo.model.bean.CookShow;
import com.example.scorpion.listviewdemo.model.bean.Gourmet;
import com.example.scorpion.listviewdemo.model.bean.HomeBean;
import com.example.scorpion.listviewdemo.model.bean.ItemType;
import com.example.scorpion.listviewdemo.model.bean.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    @BindView(R.id.listView)
    ListView mListView;
    private int[] imgArr04;
    private int[] imgArr11;
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
//                Log.i("main", TAG+", onScrollStateChanged, scrollState = "+scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Log.i("main", TAG+", onScroll, firstVisibleItem = "+firstVisibleItem+", visibleItemCount ="
//                        +visibleItemCount+", totalItemCount"+totalItemCount);
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
        imgArr11 = new int[]{
                R.drawable.tencent_safe, R.drawable.baidu_safe, R.drawable.kingsoft_safe,
                R.drawable.an_doctor, R.drawable.ruixing_safe, R.drawable.wangqin_safe,
                R.drawable.lost_safe,R.drawable.bigspider_safe,R.drawable.avg_safe,
                R.drawable.lbe_safe,R.drawable.mobile_an_safe
        };
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

        // BOUTIQUE 专辑推荐内容
        getDataForBoutique();

        // 广告01 AD
        HomeBean adBean = new HomeBean();
        adBean.setItemType(ItemType.AD);
        adBean.setAdPicIds(imgArr04);
        mList.add(adBean);

        // tag02 精选菜谱
        HomeBean tagBean03 = new HomeBean();
        tagBean03.setItemType(ItemType.TAG);
        tagBean03.setTagTitle("精选菜谱");
        mList.add(tagBean03);

        // 精选菜谱的数据内容 
        getDataForRecipe();

        // 广告02 AD
        HomeBean adBean2 = new HomeBean();
        adBean2.setItemType(ItemType.AD);
        adBean2.setAdPicIds(imgArr11);
        mList.add(adBean2);

        // 佳肴轮播模块
        getDataForCookShow();

        // tag03 美食达人
        HomeBean tagBean04 = new HomeBean();
        tagBean04.setItemType(ItemType.TAG);
        tagBean04.setTagTitle("美食达人");
        mList.add(tagBean04);

        // 美食达人模块内容
        getDataForGourmet();

        // 广告03
        mList.add(adBean); // 数据内容跟广告01相同

        addHeadBannerToListView();
        addSearchBarToListView();
    }

    private void getDataForCookShow() {
        List<CookShow> cookShowList = new ArrayList<>();
        for(int i=0; i<imgArr11.length; i++) {
            CookShow cookShow = new CookShow();
            cookShow.setAvatarId(imgArr11[i]);
            cookShow.setTitle("I'm Title "+(i+1));
            cookShow.setLikeNum(i * 5);
            cookShow.setCommentNum(i*6);
            cookShow.setUsername("username"+i);
            cookShowList.add(cookShow);
        }
        HomeBean bean = new HomeBean();
        bean.setItemType(ItemType.COOK_SHOW);
        bean.setCookShowList(cookShowList);
        mList.add(bean);
    }

    private void getDataForGourmet() {
        HomeBean bean = new HomeBean();
        bean.setItemType(ItemType.GOURMET);
        List<Gourmet> list = new ArrayList<>();
        // http://s2.cdn.xiachufang.com/ab01f4d82a4511e7947d0242ac110002_400w_225h.gif?imageView2/1/w/215/h/136/interlace/1/q/90/format/gif/.gif
        // http://s2.cdn.xiachufang.com/263e671888b511e6a9a10242ac110002_640w_640h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90
        // http://s1.cdn.xiachufang.com/f8c973446ee711e7bc9d0242ac110002_2555w_2555h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh
        // http://s1.cdn.xiachufang.com/2317ff22c57f4e8faf646b7f123c1f4c_1000w_563h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh
        // http://s1.cdn.xiachufang.com/4b108c0a888911e6b87c0242ac110003_900w_600h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh
        String[] urlArr = new String[]{
                "http://s2.cdn.xiachufang.com/ab01f4d82a4511e7947d0242ac110002_400w_225h.gif?imageView2/1/w/215/h/136/interlace/1/q/90/format/gif/.gif",
                "http://s2.cdn.xiachufang.com/263e671888b511e6a9a10242ac110002_640w_640h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90",
                "http://s1.cdn.xiachufang.com/f8c973446ee711e7bc9d0242ac110002_2555w_2555h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh",
                "http://s1.cdn.xiachufang.com/2317ff22c57f4e8faf646b7f123c1f4c_1000w_563h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh",
                "http://s1.cdn.xiachufang.com/4b108c0a888911e6b87c0242ac110003_900w_600h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh"
        };
        for(int i=0; i<imgArr11.length; i++) {
            Gourmet gourmet = new Gourmet("美食家"+i, urlArr[i%urlArr.length]);
            list.add(gourmet);
        }
        bean.setGourmetList(list);
        mList.add(bean);
    }

    private void getDataForRecipe() {
        List<Recipe> list = new ArrayList<>();
        /**
         * 产生菜谱的原数据，真实项目中从服务器下载
         */
        for (int i = 0; i < imgArr11.length; i++) {
            Recipe recipe = new Recipe();
            recipe.setPicId(imgArr11[i]);
            recipe.setTitle("第" + i + "个精选菜谱的标题");
            recipe.setIntroduce("这是精选菜谱的简介");
            recipe.setLikeNum(i * 5);
            recipe.setCommentNum(i * 6);
            list.add(recipe);
        }

        for(int i=0; i<list.size()/2; i++) {
            Recipe[] recipes = new Recipe[2];
            recipes[0] = list.get(i*2);
            recipes[1] = list.get(i*2+1);
            HomeBean bean = new HomeBean();
            bean.setItemType(ItemType.RECIPE);
            bean.setRecipeArr(recipes);
            mList.add(bean);
        }
        if (list.size() % 2 == 1) {
            Recipe[] recipes = new Recipe[2];
            recipes[0] = list.get(list.size() - 1);
            HomeBean bean = new HomeBean();
            bean.setItemType(ItemType.RECIPE);
            bean.setRecipeArr(recipes);
            mList.add(bean);
        }

    }

    private void getDataForBoutique() {
        for(int i=0; i<5; i++) {
            Boutique boutique = new Boutique();
            boutique.setTitle("第"+i+"个专辑的标题");
            boutique.setLikeNum(i * 5);
            boutique.setCommentNum(i*6);

            HomeBean bean = new HomeBean();
            bean.setItemType(ItemType.BOUTIQUE);
            bean.setBoutique(boutique);
            mList.add(bean);
        }
    }

    private void addSearchBarToListView() {
        View view = View.inflate(this, R.layout.layout_search_bar, null);
        mListView.addHeaderView(view);
    }

    private void addHeadBannerToListView() {
        // 数据
        String[] titleArr = new String[]{"第1个轮播图", "第二个轮播图", "第3个轮播图", "第四个轮播图"};



        String[] urls = new String[]{
                "https://img.alicdn.com/imgextra/i2/9/TB2IWtCdr5YBuNjSspoXXbeNFXa_!!9-0-luban.jpg_q100.jpg",
                "http://pic.qiantucdn.com/58pic/28/38/06/99q58PICzkQ_1024.jpg",
                "http://pic.qiantucdn.com/58pic/26/27/07/89V58PIC2Q5_1024.jpg",
                "http://pic.qiantucdn.com/58pic/26/93/98/05958PICPHS_1024.jpg"
        };
        List<Banner> list = new ArrayList<>();
        for(int i=0; i<titleArr.length; i++) {
            Banner banner = new Banner(titleArr[i],urls[i]);
            list.add(banner);
        }


        // holder 实例化
        HeadBannerHolder holder = new HeadBannerHolder(this, list);
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
