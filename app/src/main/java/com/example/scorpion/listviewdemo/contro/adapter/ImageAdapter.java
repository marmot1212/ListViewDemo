package com.example.scorpion.listviewdemo.contro.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * ImageAdapter继承PagerAdapter，重写其中的方法
 *
 * Created by scorpion on 2018/3/5.
 * 是ViewPager对象的适配器，填充页面内容奥ViewPager中
 * ViewPager都关联一个key对象，这里是instantiate()返回的imageView；并非直接与Views关联
 */

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private List<ImageView> mList;

    public ImageAdapter(Context context, int[] picIds) {
        mContext = context;
        mList = new ArrayList<>();
        for(int i=0; i<picIds.length; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(picIds[i]);
            mList.add(imageView);
        }
    }

    public interface OnItemClickListener{
        void setOnItemClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    /**
     * 返回可用的view的数量
     * Integer.MAX_VALUE可以保证轮播近乎永远持续下去
     */
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    /**
     * 判断页面是否跟指定的key对象关联
     * key对象由 instantiateView(ViewGroup container, int position) 返回————这里指 view(imageView)、被判断者object
     * 这些关联的view传递给 destroyItem(ViewGroup container, int position, Object object) 移出
     *
     * 小结： instantiate() 返回key—— isViewFromObject()判断——destroyItem()中移出父容器
     */
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    /**
     * 当ViewPager离开屏幕时，移出上一个object
     *
     * 在指定的位置创建页面；适配器负责添加view到这个容器中，然而它只保证在finishUpdate(ViewGroup)返回时才完成。
     */
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//        container.removeView((View)object);
    }

    @Override
    /**
     *在指定位置创建页面
     */
    public Object instantiateItem(ViewGroup container, int position) {
        // 处理position，使之位于 mList元素索引范围内
        position %= mList.size();
        if (position < 0) {
            position += mList.size();
        }

        ImageView imageView = mList.get(position % mList.size());
        // 移除 上一个view
        ViewParent vp = imageView.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(imageView);
        }
        container.addView(imageView);

        final int temp = position;
         // 设置监听事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.setOnItemClick(v, temp);
            }
        });
        return imageView;
    }
}
