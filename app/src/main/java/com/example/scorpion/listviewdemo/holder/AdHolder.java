package com.example.scorpion.listviewdemo.holder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.scorpion.listviewdemo.R;
import com.example.scorpion.listviewdemo.adapter.ImageAdapter;
import com.example.scorpion.listviewdemo.bean.HomeBean;
import com.example.scorpion.listviewdemo.utils.ToastUtil;
import com.example.scorpion.listviewdemo.view.FlowIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scorpion on 2018/3/7.
 */

public class AdHolder {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.flowIndicator)
    FlowIndicator mFlowIndicator;

    private ImageAdapter mImageAdapter;
    private Context mContext;
    private int[] picIdArr;

    public AdHolder(Context context, View convertView) {
        mContext = context;
        ButterKnife.bind(this, convertView);

        initHandler();
    }

    private Handler mHandler;
    private static final int MSG_REFRESH = 0;
    private static final int MSG_PAUSE = 1;
    private static final int MSG_CONTINUE = 2;
    private static final int MSG_PAGE_CHECK = 3;

    private static final int DELAY_TIME = 2000;
    private int currentIndex = 0;
    /**
     * 初始化 mHandler，完成自动轮播功能
     * 处理各种消息，自动播放、暂停、页面页码校正等
     */
    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //检查消息队列并移出未发送消息——避免手指触动时等复杂情况出现播放重复
                if (mHandler.hasMessages(MSG_REFRESH)) {
                    mHandler.removeMessages(MSG_REFRESH);
                }
                switch (msg.what) {
                    case MSG_REFRESH:
                        mViewPager.setCurrentItem(++currentIndex);
                        // 自己调用自己，自动轮播
                        mHandler.sendEmptyMessageDelayed(MSG_REFRESH, DELAY_TIME);
                        break;
                    case MSG_PAUSE:
                        break;
                    case MSG_CONTINUE:
                        mHandler.sendEmptyMessageDelayed(MSG_REFRESH, DELAY_TIME);
                        break;
                    case MSG_PAGE_CHECK:
                        currentIndex = msg.arg1;
                        break;
                }
            }
        };

    }


    public void setViewPager(HomeBean bean) {
        if (bean != null && bean.getAdPicIds() != null) {
            picIdArr = bean.getAdPicIds();
            if (mImageAdapter == null) {
                mImageAdapter = new ImageAdapter(mContext, picIdArr);
            }
            mViewPager.setAdapter(mImageAdapter);
            mFlowIndicator.setCount(picIdArr.length);
            mFlowIndicator.setFocus(0);
            // 启动自动轮播
            mHandler.sendEmptyMessageDelayed(MSG_REFRESH, DELAY_TIME);
            // 设置监听事件
            setListener();
        }
    }

    private void setListener() {
        setAdapterOnItemClickListener();
        setViewPagerOnScrollChangedListener();
    }

    private void setViewPagerOnScrollChangedListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mFlowIndicator.setFocus(position%picIdArr.length);
                Message message = Message.obtain(mHandler, MSG_PAGE_CHECK, position, 0);
                mHandler.sendMessage(message);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mHandler.sendEmptyMessage(MSG_PAUSE);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        mHandler.sendEmptyMessageDelayed(MSG_REFRESH, DELAY_TIME);
                        break;

                }
            }
        });
    }

    private void setAdapterOnItemClickListener() {
        mImageAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClick(View view, int position) {
                ToastUtil.showTest("广告"+position+"被点击");
            }
        });
    }

}
