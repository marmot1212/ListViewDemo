package com.example.scorpion.listviewdemo.holder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.scorpion.listviewdemo.adapter.ImageAdapter;
import com.example.scorpion.listviewdemo.R;

/**
 * Created by scorpion on 2018/3/5.
 * 包括三部分：
 * 属性和构造方法
 * 定义并实例化Handler，实现轮播的自动循环
 * 实例化mImageAdapter, 设置2个监听事件，启动轮播。监听事件中处理页面变动和界面内容变化
 */

public class HeadBannerHolder {
    private int[] mPicIdArr;
    private String[] mStringArr;
    private View convertView;
    private ViewPager mViewPager;
    private TextView mTvTitle;
    private Context mContext;

    private ImageAdapter mImageAdapter;


    public View getConvertView() {
        return convertView;
    }

    public HeadBannerHolder() {
    }

    public HeadBannerHolder(Context context,int[] picIdArr, String[] stringArr) {
        mPicIdArr = picIdArr;
        mStringArr = stringArr;
        mContext = context;
        convertView = View.inflate(mContext, R.layout.layout_head_banner, null);
        mViewPager = convertView.findViewById(R.id.viewPager_headBanner);
        mTvTitle = convertView.findViewById(R.id.tvTitle);

        initHandler();
        setViewPager();
    }

    private static Handler mHandler;
    private static final int MESSAGE_REFRESH = 0;
    private static final int MESSAGE_PAUSE= 1;
    private static final int MESSAGE_CONTINUE = 2;
    private static final int MESSAGE_PAGE_CHECK = 3;
    private static final int DELAY_TIME = 2000;
    private int currentIndex = 0;
    /**
     * 实例化mHandler
     * handleMessage() 处理自动播放、暂停、校正页面索引、继续等事件
     */
    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // 检查消息队列并移出未发送消息——避免手指触动时等复杂情况出现播放重复
                if (mHandler.hasMessages(MESSAGE_REFRESH)) {
                    mHandler.removeMessages(MESSAGE_REFRESH);
                }
                switch (msg.what) {
                    case MESSAGE_REFRESH:
                        mViewPager.setCurrentItem(++currentIndex);
                        // 启动下一次轮播
                        mHandler.sendEmptyMessageDelayed(MESSAGE_REFRESH, DELAY_TIME);
                        break;
                    case MESSAGE_PAUSE:
                        break;
                    case MESSAGE_CONTINUE:
                        mHandler.sendEmptyMessageDelayed(MESSAGE_REFRESH, DELAY_TIME);
                        break;
                    case MESSAGE_PAGE_CHECK:
                        currentIndex = msg.arg1;
                        break;
                }
            }
        };
    }

    public void setViewPager() {
        if (mImageAdapter == null) {
            mImageAdapter = new ImageAdapter(mContext, mPicIdArr);
        }
        setListener();
        mViewPager.setAdapter(mImageAdapter);
        // 初始化mViewPager
        mViewPager.setCurrentItem(0);
        mTvTitle.setText(mStringArr[0]);
        // 启动自动轮播
        mHandler.sendEmptyMessageDelayed(MESSAGE_REFRESH, DELAY_TIME);
    }

    private void setListener() {
        setAdapterOnItemClickListener();
        setViewPagerOnChangedListener();
    }

    private void setViewPagerOnChangedListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.sendMessage(Message.obtain(mHandler, MESSAGE_PAGE_CHECK, position, 0));
                // 更新轮播图的标题
                mTvTitle.setText(mStringArr[position%mStringArr.length]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mHandler.sendEmptyMessage(MESSAGE_PAUSE);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        mHandler.sendEmptyMessageDelayed(MESSAGE_REFRESH, DELAY_TIME);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setAdapterOnItemClickListener() {
        // TODO: 2018/3/6 ImageAdapter 添加监听事件接口
    }


}
