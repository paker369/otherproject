package com.example.zhugeyouliao.ui.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.zhugeyouliao.MyApplication;
import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.base.BaseActivity;
import com.example.zhugeyouliao.utils.DensityUtil;
import com.example.zhugeyouliao.utils.ImageUtils;
import com.example.zhugeyouliao.utils.JSONUtils;
import com.example.zhugeyouliao.utils.PhotoSelectSingleUtile;
import com.example.zhugeyouliao.utils.StatusBarUtils;
import com.example.zhugeyouliao.utils.StringUtils;
import com.example.zhugeyouliao.widget.CircleImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author： longyu
 * @Time： 2021/1/20 20:44
 * @description：编辑资料
 */
public class EditPersonalInfoActivity extends BaseActivity {
    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.center_title)
    TextView centerTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.civ_header)
    CircleImageView civHeader;
    @BindView(R.id.edt_nickname)
    EditText edtNickname;
    @BindView(R.id.edt_sign)
    EditText edtSign;

    //选择的图片集合
    private List<LocalMedia> mSelectList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_personal_info;
    }

    @Override
    protected void initData() {
        statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
        centerTitle.setText(getString(R.string.edt_personal_info));

        rightTitle.setText(getString(R.string.save));
        rightTitle.setBackground(getResources().getDrawable(R.drawable.shape_009afc_6));
        rightTitle.setPadding(DensityUtil.dip2px(mContext, 17), DensityUtil.dip2px(mContext, 7), DensityUtil.dip2px(mContext, 17), DensityUtil.dip2px(mContext, 7));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.rlt_header, R.id.iv_nickname_delete, R.id.iv_sign_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                //返回
                if (StringUtils.isEmpty(edtNickname.getText().toString().trim()) || StringUtils.isEmpty(edtNickname.getText().toString().trim())) {
                    return;
                }
                finish();
                break;
            case R.id.rlt_header:
                //上次头像
                PhotoSelectSingleUtile.selectPhoto(mContext, mSelectList, PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.iv_nickname_delete:
                //删除昵称
                edtNickname.setText("");
                break;
            case R.id.iv_sign_delete:
                //删除签名
                edtSign.setText("");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    mSelectList = PictureSelector.obtainMultipleResult(data);
                    if (mSelectList != null && mSelectList.size() > 0) {
                        ImageUtils.getPic(MyApplication.selectPhotoShow(mContext, mSelectList.get(0)), civHeader, mContext, R.mipmap.ic_launcher_round);
                    }
                    break;
            }
        }
    }
}
