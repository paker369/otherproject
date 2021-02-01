package com.example.zhugeyouliao.ui.login.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.base.BaseActivity;
import com.example.zhugeyouliao.http.BaseCallBack;
import com.example.zhugeyouliao.http.BaseOkHttpClient;
import com.example.zhugeyouliao.utils.InputCheckUtil;
import com.example.zhugeyouliao.utils.LogUtils;
import com.example.zhugeyouliao.utils.StatusBarUtils;
import com.example.zhugeyouliao.utils.StringUtils;
import com.example.zhugeyouliao.utils.ToastUtils;
import com.example.zhugeyouliao.widget.VerifyCodeButton;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @Author： longyu
 * @Time： 2021/1/21 12:26
 * @description： 注册
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.edt_account)
    EditText edtAccount;
    @BindView(R.id.edt_verification_code)
    EditText edtVerificationCode;
    @BindView(R.id.tv_verification_code)
    VerifyCodeButton tvVerificationCode;
    @BindView(R.id.edt_password)
    EditText edtPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        viewLine.setVisibility(View.GONE);
        statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_verification_code, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_verification_code:
                //获取验证码
                getVerificationCode();
                break;
            case R.id.tv_register:
                //注册
                onRegister();
                break;
        }
    }

    /**
     * 获取验证码
     * type (1 注册  2 修改密码   3 登录)
     */
    private void getVerificationCode() {
        String account = edtAccount.getText().toString().trim();
        if (StringUtils.isEmpty(account)) {
            toast(getString(R.string.iphone_number_not_null));
            return;
        }
        if (!InputCheckUtil.checkPhoneNum(account)) {
            ToastUtils.show(mContext, R.string.edt_right_phone);
            return;
        }
        tvVerificationCode.startTimer();
        BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.SEND_PHONE_CODE)
                .addParam("uPhone", account)
                .addParam("type", 1)
                .isLoading(true)
                .form()
                .post()
                .json()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        LogUtils.e("TAG", "---" + data);
                        toast(msg);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        toast(msg);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        toast(e.toString());
                    }
                });
    }

    /**
     * @date: 2021/1/30 15:25
     * @description 注册
     */
    private void onRegister() {
        String account = edtAccount.getText().toString().trim();
        String pwd = edtPassword.getText().toString().trim();
        String code = edtVerificationCode.getText().toString().trim();
        if (StringUtils.isEmpty(account)) {
            toast(getString(R.string.iphone_number_not_null));
            return;
        }
        if (!InputCheckUtil.checkPhoneNum(account)) {
            ToastUtils.show(mContext, R.string.edt_right_phone);
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            toast(getString(R.string.password_not_null));
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 20) {
            toast(R.string.edt_password_lenght);
            return;
        }
        if (StringUtils.isEmpty(code)) {
            toast(getString(R.string.edt_verification_code));
            return;
        }
        BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.REGISTER_USER)
                .addParam("uPhone", account)
                .addParam("uPwd", pwd)
                .addParam("code", code)
                .isLoading(true)
                .form()
                .post()
                .json()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        toast(msg);
                        finish();
                    }

                    @Override
                    public void onError(int code, String msg) {
                        toast(msg);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        toast(e.toString());
                    }
                });
    }
}
