package com.example.zhugeyouliao.ui.material.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.ui.home.adapter.RedPeopleAdapter;
import com.example.zhugeyouliao.ui.home.bena.RedPeopleBean;
import com.example.zhugeyouliao.ui.home.bena.RedPeopleListBean;
import com.example.zhugeyouliao.ui.material.adapter.MaterialTypeAdapter;
import com.example.zhugeyouliao.ui.material.bean.MaterialBean;
import com.example.zhugeyouliao.ui.material.bean.MaterialListBean;
import com.example.zhugeyouliao.ui.material.bean.MaterialTopBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Author： longyu
 * @Time： 2021/1/21 16:21
 * @description： 诸葛秘料
 */
public class MaterialTypeFrament extends LazyBaseFragments implements MaterialTypeAdapter.OnMaterialTypeItemClickListener {

    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.llyt_no_data)
    LinearLayout llytNoData;
    @BindView(R.id.srl_layout)
    SmartRefreshLayout srlLayout;

    private MaterialTypeAdapter mMaterialTypeAdapter;

    /**
     * 加载界面
     *
     * @return
     */
    public static MaterialTypeFrament newInstance(int type) {
        MaterialTypeFrament fragment = new MaterialTypeFrament();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 获取类型
     *
     * @return
     */
    private int getType() {
        return getArguments().getInt("type", 0);
    }

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.layout_refresh, null);
    }

    @Override
    public void initView() {
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    public void initData() {
        getData();
    }


     /**
      * @date: 2021/1/21 16:26
      * @description 初始化刷新
      */
    private void initRefreshLayout() {
        srlLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }
        });
        srlLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

     /**
      * @date: 2021/1/21 16:26
      * @description 初始化列表
      */
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(linearLayoutManager);
        mMaterialTypeAdapter = new MaterialTypeAdapter(mContext);
        rlvLayout.setAdapter(mMaterialTypeAdapter);
        mMaterialTypeAdapter.setOnMaterialTypeItemClickListener(this);
    }

    /**
     * @date: 2021/1/14 16:18
     * @description 获取数据
     */
    private void getData() {
        List<MaterialBean> beanList = new ArrayList<>();
        MaterialBean materialBean = new MaterialBean();

        List<MaterialTopBean> topBeans = new ArrayList<>();
        MaterialTopBean topBean = new MaterialTopBean();
        topBeans.add(topBean);
        topBeans.add(topBean);
        topBeans.add(topBean);
        topBeans.add(topBean);
        topBeans.add(topBean);
        topBeans.add(topBean);
        topBeans.add(topBean);
        topBeans.add(topBean);
        topBeans.add(topBean);

        List<MaterialListBean> listBeans = new ArrayList<>();
        MaterialListBean listBean = new MaterialListBean();
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);
        listBeans.add(listBean);

        materialBean.setTopBeans(topBeans);
        materialBean.setListBeans(listBeans);

        beanList.add(materialBean);
        beanList.add(materialBean);
        mMaterialTypeAdapter.refreshList(beanList);
    }

    @Override
    public void OnMaterialTopClick(View view, int position, int childPosition, MaterialTopBean bean) {

    }

    @Override
    public void OnMaterialListClick(View view, int position, int childPosition, MaterialListBean bean) {

    }
}
