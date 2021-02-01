package com.example.zhugeyouliao;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.zhugeyouliao.adapter.MainViewPagerAdapter;
import com.example.zhugeyouliao.base.BaseActivity;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.ui.community.fragment.CommunityFragment;
import com.example.zhugeyouliao.ui.competition.fragment.CompetitionFragment;
import com.example.zhugeyouliao.ui.home.fragment.HomeFragment;
import com.example.zhugeyouliao.ui.material.fragment.MaterialFrament;
import com.example.zhugeyouliao.ui.mine.fragment.MineFragment;
import com.example.zhugeyouliao.utils.StatusBarUtils;
import com.example.zhugeyouliao.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.vp_main)
    NoScrollViewPager vpMain;
    @BindView(R.id.rb_main_home)
    RadioButton rbMainHome;
    @BindView(R.id.rlt_main_home)
    RelativeLayout rltMainHome;
    @BindView(R.id.rb_mian_competition)
    RadioButton rbMianCompetition;
    @BindView(R.id.rlt_main_competition)
    RelativeLayout rltMainCompetition;
    @BindView(R.id.rb_main_material)
    RadioButton rbMainMaterial;
    @BindView(R.id.rlt_main_material)
    RelativeLayout rltMainMaterial;
    @BindView(R.id.rb_main_community)
    RadioButton rbMainCommunity;
    @BindView(R.id.rlt_main_community)
    RelativeLayout rltMainCommunity;
    @BindView(R.id.rb_main_mine)
    RadioButton rbMainMine;
    @BindView(R.id.rlt_main_mine)
    RelativeLayout rltMainMine;


    private long mPressedTime = 0;//退出程序使用


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.ACCESS_FINE_LOCATION
                    , Manifest.permission.CALL_PHONE
                    , Manifest.permission.CAMERA
                    , Manifest.permission.READ_LOGS
                    , Manifest.permission.READ_PHONE_STATE
                    , Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.SET_DEBUG_APP
                    , Manifest.permission.SYSTEM_ALERT_WINDOW
                    , Manifest.permission.GET_ACCOUNTS
                    , Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

        int statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        statusBarView.getLayoutParams().height = statusBarHeight;

        initViewPage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 初始化界面
     */
    private void initViewPage() {
        ArrayList<LazyBaseFragments> lazyBaseFragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        CompetitionFragment competitionFragment = new CompetitionFragment();
        MaterialFrament materialFrament = new MaterialFrament();
        CommunityFragment communityFragment = new CommunityFragment();
        MineFragment mineFragment = new MineFragment();
        lazyBaseFragments.add(homeFragment);
        lazyBaseFragments.add(competitionFragment);
        lazyBaseFragments.add(materialFrament);
        lazyBaseFragments.add(communityFragment);
        lazyBaseFragments.add(mineFragment);
        vpMain.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), lazyBaseFragments));
    }

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            Toast.makeText(this, getString(R.string.click_exit), Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {//退出程序
            this.finish();
            System.exit(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 初始化底部导航栏
     */
    private void initMainClick() {
        rbMainHome.setBackground(null);
        rbMainCommunity.setBackground(null);
        rbMainMaterial.setBackground(null);
        rbMainMine.setBackground(null);
        rbMianCompetition.setBackground(null);
    }

    @OnClick({R.id.rb_main_home, R.id.rb_mian_competition, R.id.rb_main_material, R.id.rb_main_community, R.id.rb_main_mine})
    public void onClick(View view) {
        initMainClick();
        switch (view.getId()) {
            case R.id.rb_main_home:
                rbMainHome.setBackground(getResources().getDrawable(R.drawable.home_click));
                vpMain.setCurrentItem(0);
                statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
                break;
            case R.id.rb_mian_competition:
                rbMianCompetition.setBackground(getResources().getDrawable(R.drawable.home_click));
                vpMain.setCurrentItem(1);
                statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
                break;
            case R.id.rb_main_material:
                rbMainMaterial.setBackground(getResources().getDrawable(R.drawable.home_click));
                vpMain.setCurrentItem(2);
                statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
                break;
            case R.id.rb_main_community:
                rbMainCommunity.setBackground(getResources().getDrawable(R.drawable.home_click));
                vpMain.setCurrentItem(3);
                statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
                break;
            case R.id.rb_main_mine:
                rbMainMine.setBackground(getResources().getDrawable(R.drawable.home_click));
                vpMain.setCurrentItem(4);
                statusBarView.getLayoutParams().height = 0;
                break;
        }
    }
}
