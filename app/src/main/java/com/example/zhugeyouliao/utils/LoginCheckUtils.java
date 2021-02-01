package com.example.zhugeyouliao.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zhugeyouliao.MyApplication;
import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.base.BasicBean;
import com.example.zhugeyouliao.ui.login.activity.LoginActivity;
import com.example.zhugeyouliao.ui.login.bean.UserBean;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.v3.MessageDialog;

/**
 * @Author： longyu
 * @Time： 2021/1/20 20:36
 * @description： 用户是否登录检测工具类
 */
public class LoginCheckUtils {

    //显示未登录提示框
    public static void showLogin(Activity activity) {
        MessageDialog.show((AppCompatActivity) activity,
                activity.getResources().getString(R.string.warm_prompt),
                activity.getResources().getString(R.string.is_login),
                activity.getResources().getString(R.string.confirm),
                activity.getResources().getString(R.string.cancel))
                .setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {
                        baseDialog.doDismiss();
                        MyApplication.openActivity(activity, LoginActivity.class);
                        return false;
                    }
                });
    }

    /**
     * 检查用户是否登录
     *
     * @return
     */
    public static boolean checkUserIsLogin() {
        String token = MyApplication.mPreferenceProvider.getToken();
        if (!StringUtils.isEmpty(token)) {
            return true;
        }
        return false;
    }

    //检查是否登录
    public static boolean checkLoginShowDialog(Activity mContext) {
        if (!LoginCheckUtils.checkUserIsLogin()) {
            LoginCheckUtils.showLogin(mContext);
            return false;

        }
        return true;
    }

    //保存登录信息
    public static void saveLoginInfo(UserBean infoBean) {
        MyApplication.mPreferenceProvider.setToken(infoBean.getUToken());
        MyApplication.mPreferenceProvider.setUserId(infoBean.getUId() + "");
    }

    //清空登录信息和授权信息
    public static void clearUserInfo(Activity activity) {
        MyApplication.mPreferenceProvider.setToken("");
        MyApplication.mPreferenceProvider.setUserId("");
    }

}
