package com.example.zhugeyouliao.ui.community.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.MyApplication;
import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.ui.community.activity.CommunityPlazaTypeActivity;
import com.example.zhugeyouliao.ui.community.adapter.CommunityPlazaListAdapter;
import com.example.zhugeyouliao.ui.community.adapter.CommunityPlazaTypeAdapter;
import com.example.zhugeyouliao.ui.community.bean.CommunityPlazaListBean;
import com.example.zhugeyouliao.ui.community.bean.CommunityPlazaTypeBean;
import com.example.zhugeyouliao.utils.LoginCheckUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author： longyu
 * @Time： 2021/1/23 7:21
 * @description： 社区 广场
 */
public class CommunityPlazaFragment extends LazyBaseFragments {

    @BindView(R.id.rlv_plaza_type)
    RecyclerView rlvPlazaType;
    @BindView(R.id.rlv_plaze_list)
    RecyclerView rlvPlazeList;
    @BindView(R.id.tv_plaza_login)
    TextView tvPlazaLogin;
    @BindView(R.id.llt_mine)
    LinearLayout lltMine;
    @BindView(R.id.srl_plaza_refresh)
    SmartRefreshLayout srlPlazaRefresh;

    private Integer[][] mType = new Integer[][]{{R.mipmap.ic_plaza_mine, R.string.main_mine}, {R.mipmap.ic_plaza_football, R.string.plaza_football}
            , {R.mipmap.ic_plaza_basketball, R.string.plaza_basketball}, {R.mipmap.ic_plaza_game, R.string.plaza_game}
            , {R.mipmap.ic_plaza_chat, R.string.plaza_chat}, {R.mipmap.ic_plaza_comprehensive_sports, R.string.plaza_comprehensive_sports}
            , {R.mipmap.ic_plaza_administratives, R.string.plaza_administratives}, {R.mipmap.ic_plaza_disclose, R.string.plaza_disclose}};
    private List<CommunityPlazaTypeBean> mTypeBeans = new ArrayList<>();
    //足球
    private Integer[][] mFootballList = new Integer[][]{{R.mipmap.ic_plaza_china_football, R.string.plaza_china_football},
            {R.mipmap.ic_plaza_international_football, R.string.plaza_international_football}
            , {R.mipmap.ic_plaza_european_cup, R.string.plaza_european_cup},
            {R.mipmap.ic_plaza_league, R.string.plaza_league}};
    //篮球
    private Integer[][] mBasketballList = new Integer[][]{{R.mipmap.ic_plaza_basketball_nba, R.string.plaza_basketball_nba},
            {R.mipmap.ic_plaza_basketball_cba, R.string.plaza_basketball_cba}
            , {R.mipmap.ic_plaza_basketball_hj, R.string.plaza_basketball_hj},
            {R.mipmap.ic_plaza_basketball_hr, R.string.plaza_basketball_hr}};
    //电竞
    private Integer[][] mGameList = new Integer[][]{{R.mipmap.ic_plaza_game_news, R.string.plaza_game_news},
            {R.mipmap.ic_plaza_game_lol, R.string.plaza_game_lol}
            , {R.mipmap.ic_plaza_game_cs, R.string.plaza_game_cs},
            {R.mipmap.ic_plaza_game_dota, R.string.plaza_game_dota}};
    //闲聊
    private Integer[][] mChatList = new Integer[][]{{R.mipmap.ic_plaza_chat_live, R.string.plaza_chat_live},
            {R.mipmap.ic_plaza_chat_pet_dating, R.string.plaza_chat_pet_dating}
            , {R.mipmap.ic_plaza_chat_freshman, R.string.plaza_chat_freshman},
            {R.mipmap.ic_plaza_chat_news, R.string.plaza_chat_news}};
    //综合体育
    private Integer[][] mComprehensiveSportsList = new Integer[][]{{R.mipmap.ic_plaza_comprehensive_sports_wwe, R.string.plaza_comprehensive_sports_wwe},
            {R.mipmap.ic_plaza_comprehensive_sports_badminton, R.string.plaza_comprehensive_sports_badminton}
            , {R.mipmap.ic_plaza_comprehensive_sports_freshman, R.string.plaza_comprehensive_sports_freshman},
            {R.mipmap.ic_plaza_comprehensive_sports_f1, R.string.plaza_comprehensive_sports_f1}};
    //站务管理
    private Integer[][] mAdministrativessList = new Integer[][]{{R.mipmap.ic_plaza_administratives_disclose, R.string.plaza_administratives_disclose},
            {R.mipmap.ic_plaza_administratives_opinion, R.string.plaza_administratives_opinion}};
    //产品吐槽
    private Integer[][] mDiscloseList = new Integer[][]{{R.mipmap.ic_plaza_administratives_disclose, R.string.plaza_disclose_prefecture}};

    private CommunityPlazaTypeAdapter mTypeAdapter;

    private CommunityPlazaListAdapter mListAdapter;

    /**
     * 加载界面
     *
     * @return
     */
    public static CommunityPlazaFragment newInstance() {
        CommunityPlazaFragment fragment = new CommunityPlazaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_community_plaza, null);
    }

    @Override
    public void initView() {
        initRefreshLayout();
        initListRecyclerView();
        initRecyclerView();
    }

    @Override
    public void initData() {
        initListData();
    }

    /**
     * @date: 2021/1/23 20:40
     * @description 初始化数据
     */
    private void initListData() {
        for (int i = 0; i < mType.length; i++) {
            CommunityPlazaTypeBean typeBean = new CommunityPlazaTypeBean();
            typeBean.setImg(mType[i][0]);
            typeBean.setName(getString(mType[i][1]));
            switch (i) {
                case 0:
                    break;
                case 1:
                    List<CommunityPlazaListBean> mFootballListBeans = new ArrayList<>();
                    for (int j = 0; j < mFootballList.length; j++) {
                        CommunityPlazaListBean listBean = new CommunityPlazaListBean();
                        listBean.setImg(mFootballList[j][0]);
                        listBean.setName(getString(mFootballList[j][1]));
                        mFootballListBeans.add(listBean);
                    }
                    typeBean.setListBeans(mFootballListBeans);
                    break;
                case 2:
                    List<CommunityPlazaListBean> mBasketballListBeans = new ArrayList<>();
                    for (int j = 0; j < mBasketballList.length; j++) {
                        CommunityPlazaListBean listBean = new CommunityPlazaListBean();
                        listBean.setImg(mBasketballList[j][0]);
                        listBean.setName(getString(mBasketballList[j][1]));
                        mBasketballListBeans.add(listBean);
                    }
                    typeBean.setListBeans(mBasketballListBeans);
                    break;
                case 3:
                    List<CommunityPlazaListBean> mGameListBeans = new ArrayList<>();
                    for (int j = 0; j < mGameList.length; j++) {
                        CommunityPlazaListBean listBean = new CommunityPlazaListBean();
                        listBean.setImg(mGameList[j][0]);
                        listBean.setName(getString(mGameList[j][1]));
                        mGameListBeans.add(listBean);
                    }
                    typeBean.setListBeans(mGameListBeans);
                    break;
                case 4:
                    List<CommunityPlazaListBean> mChatListBeans = new ArrayList<>();
                    for (int j = 0; j < mChatList.length; j++) {
                        CommunityPlazaListBean listBean = new CommunityPlazaListBean();
                        listBean.setImg(mChatList[j][0]);
                        listBean.setName(getString(mChatList[j][1]));
                        mChatListBeans.add(listBean);
                    }
                    typeBean.setListBeans(mChatListBeans);
                    break;
                case 5:
                    List<CommunityPlazaListBean> mComprehensiveSportsListBeans = new ArrayList<>();
                    for (int j = 0; j < mComprehensiveSportsList.length; j++) {
                        CommunityPlazaListBean listBean = new CommunityPlazaListBean();
                        listBean.setImg(mComprehensiveSportsList[j][0]);
                        listBean.setName(getString(mComprehensiveSportsList[j][1]));
                        mComprehensiveSportsListBeans.add(listBean);
                    }
                    typeBean.setListBeans(mComprehensiveSportsListBeans);
                    break;
                case 6:
                    List<CommunityPlazaListBean> mAdministrativessListBeans = new ArrayList<>();
                    for (int j = 0; j < mAdministrativessList.length; j++) {
                        CommunityPlazaListBean listBean = new CommunityPlazaListBean();
                        listBean.setImg(mAdministrativessList[j][0]);
                        listBean.setName(getString(mAdministrativessList[j][1]));
                        mAdministrativessListBeans.add(listBean);
                    }
                    typeBean.setListBeans(mAdministrativessListBeans);
                    break;
                case 7:
                    List<CommunityPlazaListBean> mDiscloseListBeans = new ArrayList<>();
                    for (int j = 0; j < mDiscloseList.length; j++) {
                        CommunityPlazaListBean listBean = new CommunityPlazaListBean();
                        listBean.setImg(mDiscloseList[j][0]);
                        listBean.setName(getString(mDiscloseList[j][1]));
                        mDiscloseListBeans.add(listBean);
                    }
                    typeBean.setListBeans(mDiscloseListBeans);
                    break;
            }
            mTypeBeans.add(typeBean);
        }
        mTypeBeans.get(0).setSelect(true);
        mTypeAdapter.refreshList(mTypeBeans);
    }

    /**
     * @date: 2021/1/23 7:38
     * @description 初始化刷新
     */
    private void initRefreshLayout() {
        srlPlazaRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }
        });
        srlPlazaRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    /**
     * @date: 2021/1/23 7:38
     * @description 初始化列表
     */
    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        rlvPlazaType.setLayoutManager(gridLayoutManager);
        mTypeAdapter = new CommunityPlazaTypeAdapter(mContext);
        rlvPlazaType.setAdapter(mTypeAdapter);
        mTypeAdapter.setOnItemClickListener(new AFinalRecyclerViewAdapter.OnItemClickListener<CommunityPlazaTypeBean>() {
            @Override
            public void onItemClick(View view, int position, CommunityPlazaTypeBean model) {
                if (position == 0) {
                    if (!LoginCheckUtils.checkUserIsLogin()) {
                        lltMine.setVisibility(View.VISIBLE);
                    }
                } else {
                    lltMine.setVisibility(View.GONE);
                }
                mListAdapter.refreshList(model.getListBeans());
            }

            @Override
            public void onItemLongClick(View view, int position, CommunityPlazaTypeBean model) {

            }
        });
    }

    /**
     * @date: 2021/1/23 8:36
     * @description 初始化列表
     */
    private void initListRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        rlvPlazeList.setLayoutManager(gridLayoutManager);
        mListAdapter = new CommunityPlazaListAdapter(mContext);
        rlvPlazeList.setAdapter(mListAdapter);

        mListAdapter.setOnItemClickListener(new AFinalRecyclerViewAdapter.OnItemClickListener<CommunityPlazaListBean>() {
            @Override
            public void onItemClick(View view, int position, CommunityPlazaListBean model) {
                Bundle bundle = new Bundle();
                bundle.putString("title", model.getName());
                MyApplication.openActivity(mContext, CommunityPlazaTypeActivity.class, bundle);
            }

            @Override
            public void onItemLongClick(View view, int position, CommunityPlazaListBean model) {

            }
        });
    }


    @OnClick(R.id.tv_plaza_login)
    public void onClick() {
        //登录
    }
}
