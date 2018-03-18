package com.example.scorpion.listviewdemo.contro.holder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.scorpion.listviewdemo.R;
import com.example.scorpion.listviewdemo.contro.adapter.ImageAdapter;
import com.example.scorpion.listviewdemo.model.bean.CookShow;
import com.example.scorpion.listviewdemo.model.bean.HomeBean;
import com.example.scorpion.listviewdemo.model.utils.DisplayTool;
import com.example.scorpion.listviewdemo.model.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scorpion on 2018/3/7.
 */

public class CookShowHolder {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.ivAvatar)
    ImageView mIvAvatar;
    @BindView(R.id.tvUsername)
    TextView mTvUsername;
    @BindView(R.id.tvCommentNum)
    TextView mTvCommentNum;
    @BindView(R.id.tvLikeNum)
    TextView mTvLikeNum;
    @BindView(R.id.layout_username)
    RelativeLayout mLayoutUsername;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.layout_indicator)
    LinearLayout mLayoutIndicator;

    private Context mContext;
    private List<CookShow>  mList;
    private ImageAdapter mAdapter;

    // 轮播指示器
    private DisplayTool mDisplayTool;
    private int preIndex = 0;

    public CookShowHolder(Context context, View convertView) {
        mContext = context;
        ButterKnife.bind(this, convertView);

        mDisplayTool = new DisplayTool(mContext);
        initHandler();
    }

    private Handler mHandler;
    private static final int MSG_REFRESH = 0;
    private static final int MSG_PAUSE= 1;
    private static final int MSG_CONTINUE = 2;
    private static final int MSG_PAGE_CHECK = 3;

    private static final int DELAY_TIME = 2000;
    private int currentIndex = 0;

    /**
     * 初始化 mHandler
     * 处理自动轮播的相关事件：滑动、暂停、继续、核对页码、自己调用自己
     * 开始检查是否有未发送信息，若有移除——防止复杂情况出现页码重复
     */
    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (mHandler.hasMessages(MSG_REFRESH)) {
                    mHandler.removeMessages(MSG_REFRESH);
                }
                switch (msg.what) {
                    case MSG_REFRESH:
                        mViewPager.setCurrentItem(++currentIndex);
                        // 实现自动播放的核心代码
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
        if (bean != null) {
            mList = bean.getCookShowList();
        }

        int[] picIds = new int[mList.size()];
        // 初始化指示器 mLayoutIndicator
        View dot;
        LinearLayout.LayoutParams params;
        for(int i=0; i<picIds.length; i++) {
            picIds[i] = mList.get(i).getPicId();
            params = new LinearLayout.LayoutParams(mDisplayTool.dip2px(10),mDisplayTool.dip2px(10));
            params.leftMargin = mDisplayTool.dip2px(10);
            dot = new View(mContext);
            dot.setBackgroundResource(R.drawable.indicator_seclector);
            dot.setEnabled(false);
            dot.setLayoutParams(params);
            mLayoutIndicator.addView(dot);
        }

        mAdapter = new ImageAdapter(mContext, picIds);
        mViewPager.setAdapter(mAdapter);

        setListener();
        // 初始化ViewPager并启动轮播
        mViewPager.setCurrentItem(0);
        refreshUI(0);
        mLayoutIndicator.getChildAt(0).setEnabled(true);
        mHandler.sendEmptyMessageDelayed(MSG_REFRESH, DELAY_TIME);
    }

    private void refreshUI(int position) {
        int temp = position%mList.size();
        mIvAvatar.setImageResource(mList.get(temp).getAvatarId());
        mTvUsername.setText(mList.get(temp).getUsername());
        mTvLikeNum.setText(mList.get(temp).getLikeNum() + "");
        mTvCommentNum.setText(mList.get(temp).getCommentNum() + "");
        mTvTitle.setText(mList.get(temp).getTitle());
    }

    private void setListener() {
        setAdapterOnItemClickListener();
        setViewPagerOnScrollChangeListener();
    }

    private void setViewPagerOnScrollChangeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.sendMessage(Message.obtain(mHandler, MSG_PAGE_CHECK, position, 0));
                // 修改页码的其他内容与轮播图片一致
                refreshUI(position);
                // 修改 指示器焦点
                int temp = position%mList.size();
                mLayoutIndicator.getChildAt(preIndex).setEnabled(false);
                mLayoutIndicator.getChildAt(temp).setEnabled(true);
                preIndex = temp;

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
        mAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClick(View view, int position) {
                ToastUtil.showTest("I'm cook show 列表项"+position+", 刚被点击");
            }
        });
    }


}
