package com.example.scorpion.listviewdemo.holder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scorpion.listviewdemo.R;
import com.example.scorpion.listviewdemo.utils.ToastUtil;
import com.example.scorpion.listviewdemo.bean.HomeBean;
import com.example.scorpion.listviewdemo.bean.Recipe;


import java.util.Arrays;

import butterknife.ButterKnife;

/**
 * Created by scorpion on 2018/3/7.
 */

public class RecipeHolder {
    ImageView mIvThumb;
    TextView mTvTitle;
    TextView mTvLikeNum;
    TextView mTvCommentNum;
    ImageView mIvThumb02;
    TextView mTvTitle02;
    TextView mTvLikeNum02;
    TextView mTvCommentNum02;

    private LinearLayout layout01, layout02;

    public RecipeHolder(View convertView) {
        ButterKnife.bind(this, convertView);
        layout01 = convertView.findViewById(R.id.layout01);
        layout02 = convertView.findViewById(R.id.layout02);

        mIvThumb = layout01.findViewById(R.id.ivThumb);
        mTvTitle = layout01.findViewById(R.id.tvTitle);
        mTvLikeNum = layout01.findViewById(R.id.tvLikeNum);
        mTvCommentNum = layout01.findViewById(R.id.tvCommentNum);

        mIvThumb02 = layout02.findViewById(R.id.ivThumb);
        mTvTitle02 = layout02.findViewById(R.id.tvTitle);
        mTvLikeNum02 = layout02.findViewById(R.id.tvLikeNum);
        mTvCommentNum02 = layout02.findViewById(R.id.tvCommentNum);

        layout01.setOnClickListener(mOnClickListener);
        layout02.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            TextView view = v.findViewById(R.id.tvTitle);
            ToastUtil.showTest(view.getText().toString()+"被点击");
        }
    };

    public void refreshUI(HomeBean bean) {
        if (bean != null && bean.getRecipeArr()!=null) {
            Recipe[] recipes = bean.getRecipeArr();
            if (recipes[1] != null) {
                layout02.setVisibility(View.VISIBLE);
                refreshUiLeft(recipes[0]);
                refreshUiRight(recipes[1]);
            } else {
                layout02.setVisibility(View.INVISIBLE);
                refreshUiLeft(recipes[0]);
            }

        }
    }

    private void refreshUiRight(Recipe recipe) {
        if (recipe != null) {
            mIvThumb02.setImageResource(recipe.getPicId());
            mTvTitle02.setText(recipe.getTitle());
            mTvLikeNum02.setText(recipe.getLikeNum()+"");
            mTvCommentNum02.setText(recipe.getCommentNum()+"");
        }
    }

    private void refreshUiLeft(Recipe recipe) {
        if (recipe != null) {
            mIvThumb.setImageResource(recipe.getPicId());
            mTvTitle.setText(recipe.getTitle());
            mTvLikeNum.setText(recipe.getLikeNum()+"");
            mTvCommentNum.setText(recipe.getCommentNum()+"");
        }
    }

}
