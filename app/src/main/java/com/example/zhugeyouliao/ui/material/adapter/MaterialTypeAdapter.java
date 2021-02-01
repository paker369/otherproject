package com.example.zhugeyouliao.ui.material.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.home.adapter.RecommendAdpater;
import com.example.zhugeyouliao.ui.material.bean.MaterialBean;
import com.example.zhugeyouliao.ui.material.bean.MaterialListBean;
import com.example.zhugeyouliao.ui.material.bean.MaterialTopBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/21 16:28
 * @description： 诸葛秘料
 */
public class MaterialTypeAdapter extends AFinalRecyclerViewAdapter<MaterialBean> {

    public static final int HOME_ITEM_0 = 0;
    public static final int HOME_ITEM_1 = 1;

    private OnMaterialTypeItemClickListener mOnMaterialTypeItemClickListener;

    public void setOnMaterialTypeItemClickListener(OnMaterialTypeItemClickListener mOnMaterialTypeItemClickListener) {
        this.mOnMaterialTypeItemClickListener = mOnMaterialTypeItemClickListener;
    }

    public interface OnMaterialTypeItemClickListener {
        void OnMaterialTopClick(View view, int position, int childPosition, MaterialTopBean bean);

        void OnMaterialListClick(View view, int position, int childPosition, MaterialListBean bean);
    }

    public MaterialTypeAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOME_ITEM_0) {
            return new TopViewHolder(m_Inflater.inflate(R.layout.layout_recycleview, parent, false));
        } else {
            return new ListViewHolder(m_Inflater.inflate(R.layout.item_material_type, parent, false));
        }
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HOME_ITEM_0:
                ((TopViewHolder) holder).show(position);
                break;
            case HOME_ITEM_1:
                ((ListViewHolder) holder).show(position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HOME_ITEM_0;
        } else if (position == 1) {
            return HOME_ITEM_1;
        }
        return super.getItemViewType(position);
    }

    /**
     * 头部
     */
    public class TopViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.rlv_layout)
        RecyclerView rlvLayout;

        public TopViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(m_Context);
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            rlvLayout.setLayoutManager(layoutManager);
            MaterialTypeTopItemAdapter topItemAdapter = new MaterialTypeTopItemAdapter(m_Activity);
            rlvLayout.setAdapter(topItemAdapter);
            topItemAdapter.refreshList(getItem(position).getTopBeans());
            topItemAdapter.setOnItemClickListener(new OnItemClickListener<MaterialTopBean>() {
                @Override
                public void onItemClick(View view, int childPosition, MaterialTopBean model) {
                    if (mOnMaterialTypeItemClickListener != null) {
                        mOnMaterialTypeItemClickListener.OnMaterialTopClick(view, position, childPosition, model);
                    }
                }

                @Override
                public void onItemLongClick(View view, int position, MaterialTopBean model) {

                }
            });
        }
    }

    /**
     * 列表
     */
    public class ListViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.rlv_layout)
        RecyclerView rlvLayout;

        public ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {

            LinearLayoutManager layoutManager = new LinearLayoutManager(m_Activity);
            rlvLayout.setLayoutManager(layoutManager);
            MaterialTypeListItemAdapter itemAdapter = new MaterialTypeListItemAdapter(m_Activity);
            rlvLayout.setAdapter(itemAdapter);
            itemAdapter.refreshList(getItem(position).getListBeans());
            itemAdapter.setOnItemClickListener(new OnItemClickListener<MaterialListBean>() {
                @Override
                public void onItemClick(View view, int childPosition, MaterialListBean model) {
                    if (mOnMaterialTypeItemClickListener != null) {
                        mOnMaterialTypeItemClickListener.OnMaterialListClick(view, position, childPosition, model);
                    }
                }

                @Override
                public void onItemLongClick(View view, int position, MaterialListBean model) {

                }
            });
        }
    }


}
