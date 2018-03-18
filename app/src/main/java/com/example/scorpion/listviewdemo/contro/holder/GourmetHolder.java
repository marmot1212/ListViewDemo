package com.example.scorpion.listviewdemo.contro.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scorpion.listviewdemo.R;
import com.example.scorpion.listviewdemo.model.bean.Gourmet;
import com.example.scorpion.listviewdemo.model.bean.HomeBean;
import com.example.scorpion.listviewdemo.model.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scorpion on 2018/3/7.
 */

public class GourmetHolder  {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private Context mContext;
    private List<Gourmet> mGourmetList;
    private GourmetAdapter mAdapter;

    public GourmetHolder(Context context, View couvertView) {
        mContext = context;
        ButterKnife.bind(this, couvertView);
    }

    public void initAdapter(HomeBean bean) {
        if (bean != null) {
            mGourmetList = bean.getGourmetList();
        }
        mAdapter = new GourmetAdapter(mContext, mGourmetList);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
    }


    class GourmetAdapter extends RecyclerView.Adapter<GourmetAdapter.ItemViewHolder> {
        Context context;
        List<Gourmet> list;

        public GourmetAdapter(Context context, List<Gourmet> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.layout_gourmet_item, null);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            final Gourmet gourmet = list.get(position);
            holder.refreshUI(gourmet);
            holder.ivThumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showTest("列表项被点击："+gourmet.toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.ivThumb)
            ImageView ivThumb;
            @BindView(R.id.tvTitle)
            TextView tvTitle;

            ItemViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }

            public void refreshUI(Gourmet gourmet) {
                if (gourmet != null) {
                    ivThumb.setImageResource(gourmet.getPicId());
                    tvTitle.setText(gourmet.getTitle());
                }
            }
        }
    }
}
