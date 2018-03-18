package com.example.scorpion.listviewdemo.contro.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scorpion.listviewdemo.R;
import com.example.scorpion.listviewdemo.model.bean.Boutique;
import com.example.scorpion.listviewdemo.model.bean.HomeBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scorpion on 2018/3/7.
 */

public class BoutiqueHolder {
    @BindView(R.id.ivThumb)
    ImageView mIvThumb;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvContent)
    TextView mTvContent;
    @BindView(R.id.tvCommentNum)
    TextView mTvCommentNum;
    @BindView(R.id.tvLikeNum)
    TextView mTvLikeNum;

    public BoutiqueHolder(View convertView) {
        if(convertView !=null) ButterKnife.bind(this, convertView);
    }

    public void refreshUI(HomeBean bean) {
        if (bean != null) {
            Boutique boutique = bean.getBoutique();
            mIvThumb.setImageResource(boutique.getPicId());
            mTvTitle.setText(boutique.getTitle());
//            mTvContent.setText(boutique.getContent()); // 内容为空，注释掉
            mTvLikeNum.setText(boutique.getLikeNum()+"");
            mTvCommentNum.setText(boutique.getCommentNum()+"");
        }
    }
}
