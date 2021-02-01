package com.example.zhugeyouliao.ui.login.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhugeyouliao.MainActivity;
import com.example.zhugeyouliao.MyApplication;
import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.base.BaseActivity;
import com.example.zhugeyouliao.http.BaseCallBack;
import com.example.zhugeyouliao.http.BaseOkHttpClient;
import com.example.zhugeyouliao.ui.login.bean.UserBean;
import com.example.zhugeyouliao.utils.InputCheckUtil;
import com.example.zhugeyouliao.utils.JSONUtils;
import com.example.zhugeyouliao.utils.LogUtils;
import com.example.zhugeyouliao.utils.LoginCheckUtils;
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
 * @Time： 2021/1/21 10:39
 * @description： 登录界面
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_account_login)
    TextView tvAccountLogin;
    @BindView(R.id.tv_verification_code_login)
    TextView tvVerificationCodeLogin;
    @BindView(R.id.edt_account)
    EditText edtAccount;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.tv_verification_code)
    VerifyCodeButton tvVerificationCode;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    //(1  密码登录   2 验证码登录)
    private int mType = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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

    @OnClick({R.id.tv_account_login, R.id.tv_verification_code_login, R.id.rl_back, R.id.tv_verification_code, R.id.tv_register, R.id.tv_forget_password, R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_account_login:
                //账号登录
                tvAccountLogin.setBackground(getResources().getDrawable(R.drawable.shape_4d1cf2_14_top));
                tvVerificationCodeLogin.setBackground(null);
                tvVerificationCode.setVisibility(View.GONE);
                tvRegister.setVisibility(View.VISIBLE);
                tvForgetPassword.setVisibility(View.VISIBLE);
                edtPassword.setHint(getString(R.string.edt_password));
                mType = 1;
                break;
            case R.id.tv_verification_code_login:
                //验证码登录
                tvVerificationCodeLogin.setBackground(getResources().getDrawable(R.drawable.shape_4d1cf2_14_top));
                tvAccountLogin.setBackground(null);
                tvVerificationCode.setVisibility(View.VISIBLE);
                tvRegister.setVisibility(View.GONE);
                tvForgetPassword.setVisibility(View.GONE);
                edtPassword.setHint(getString(R.string.edt_verification_code));
                mType = 2;
                break;
            case R.id.tv_verification_code:
                //获取验证码
                getVerificationCode();
                break;
            case R.id.tv_register:
                //注册
                MyApplication.openActivity(mContext, RegisterActivity.class);
                break;
            case R.id.tv_forget_password:
                //忘记密码
                MyApplication.openActivity(mContext, ForgetPasswordActivity.class);
                break;
            case R.id.tv_login:
                //登录
                onLogin();
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
      * @date: 2021/1/30 15:39
      * @description 登录
      */
    private void onLogin() {
        String account = edtAccount.getText().toString().trim();
        String pwd = edtPassword.getText().toString().trim();
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
        BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.LOGIN)
                .addParam("uPhone", account)
                .addParam("uPwd", pwd)
                .addParam("type", mType)
                .isLoading(true)
                .form()
                .post()
                .json()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        toast(msg);
                        UserBean userBean = JSONUtils.jsonString2Bean(data, UserBean.class);
                        if (userBean != null){
                            LoginCheckUtils.saveLoginInfo(userBean);
                            MyApplication.openActivity(mContext, MainActivity.class);
                            finish();
                        }
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
