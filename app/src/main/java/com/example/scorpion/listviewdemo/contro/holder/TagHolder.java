package com.example.scorpion.listviewdemo.contro.holder;

import android.view.View;
import android.widget.TextView;

import com.example.scorpion.listviewdemo.R;
import com.example.scorpion.listviewdemo.model.bean.HomeBean;

/**
 * Created by scorpion on 2018/3/6.
 */

public class TagHolder {
    private TextView mTvTitle;

    public TagHolder(View convertView) {
        mTvTitle = convertView.findViewById(R.id.tvTitle);
    }

    public void refreshUI(HomeBean bean) {
        if (bean != null) {
            mTvTitle.setText(bean.getTagTitle());
        }
    }
}
