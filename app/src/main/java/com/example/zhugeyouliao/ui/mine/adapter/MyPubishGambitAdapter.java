package com.example.zhugeyouliao.ui.mine.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.mine.bean.MyPublishGambitBean;
import com.example.zhugeyouliao.widget.CircleImageView;
import com.shehuan.niv.NiceImageView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Author： longyu
 * @Time： 2021/1/21 13:11
 * @description： 我发布的话题
 */
public class MyPubishGambitAdapter extends AFinalRecyclerViewAdapter<MyPublishGambitBean> {

    public MyPubishGambitAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_my_collect, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.civ_collect_header)
        CircleImageView civCollectHeader;
        @BindView(R.id.tv_collect_name)
        TextView tvCollectName;
        @BindView(R.id.tv_collect_title)
        TextView tvCollectTitle;
        @BindView(R.id.tv_collect_content)
        TextView tvCollectContent;
        @BindView(R.id.niv_collect_img)
        NiceImageView nivCollectImg;
        @BindView(R.id.tv_collect_time)
        TextView tvCollectTime;
        @BindView(R.id.tv_collect_like)
        TextView tvCollectLike;
        @BindView(R.id.tv_collect_comment)
        TextView tvCollectComment;
        @BindView(R.id.tv_collect_read)
        TextView tvCollectRead;
        @BindView(R.id.tv_delete)
        TextView tvDelete;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {


            tvCollectComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position, getItem(position));
                    }
                }
            });
            tvCollectLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position, getItem(position));
                    }
                }
            });
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position, getItem(position));
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position, getItem(position));
                    }
                }
            });
        }

    }

}
