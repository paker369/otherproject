package com.example.zhugeyouliao.http;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.example.zhugeyouliao.config.Constants;
import com.example.zhugeyouliao.utils.JSONUtils;
import com.example.zhugeyouliao.utils.LogUtils;
import com.example.zhugeyouliao.utils.StringUtils;
import com.example.zhugeyouliao.utils.StyledDialogUtils;
import com.example.zhugeyouliao.utils.ToastUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Description:OkHttpMange管理类
 *
 * @author zjn
 * Email：168455992@qq.com
 * @date 2019/1/11
 */
public class OkHttpManager {
    private static OkHttpManager mInstance;
    private OkHttpClient mClient;
    private Handler mHandler;

    /**
     * 单例
     *
     * @return
     */
    public static synchronized OkHttpManager getInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpManager();
        }
        return mInstance;
    }

    /**
     * 构造函数
     */
    private OkHttpManager() {
        initOkHttp();
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 初始化OkHttpClient
     */
    private void initOkHttp() {
        mClient = new OkHttpClient().newBuilder()
                .readTimeout(30000, TimeUnit.SECONDS)
                .connectTimeout(30000, TimeUnit.SECONDS)
                .writeTimeout(30000, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 请求
     *
     * @param client
     * @param callBack
     */
    public void request(final Activity activity, BaseOkHttpClient client, final boolean isLoading, final BaseCallBack callBack) {
        if (callBack == null) {
            throw new NullPointerException(" callback is null");
        }
        if (isLoading) {
            StyledDialogUtils.getInstance().loading(activity);
        }
        mClient.newCall(client.buildRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (isLoading) {
                    StyledDialogUtils.getInstance().dismissLoading();
                }
                LogUtils.e("TAG", "************ FailureMessage1 =" + e.getMessage() + client.toString());
                activity.runOnUiThread(() -> ToastUtils.show(activity, "服务器异常，请稍后再试"));
                sendOnFailureMessage(callBack, call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (isLoading) {
                    StyledDialogUtils.getInstance().dismissLoading();
                }
                if (response.isSuccessful()) {
                    String result = response.body().string().trim();
                    LogUtils.e("TAG", "************ ResultJson =" + result);
                    try {
                        if (!StringUtils.isEmpty(result)) {
                            String code = JSONUtils.getNoteJson(result, "code");
                            String msg = JSONUtils.getNoteJson(result, "msg");
                            String data = JSONUtils.getNoteJson(result, "data");
                            if ("200".equals(code)) {
                                //请求成功
                                sendOnSuccessMessage(callBack, data, msg);
                            } else
                                sendOnErrorMessage(callBack, Integer.parseInt(code), msg);
                        }
                    } catch (Exception e) {
                        activity.runOnUiThread(() -> ToastUtils.show(activity, "服务器异常，请稍后再试"));
                        LogUtils.e("TAG", "************ FailureMessage2 =" + e.getMessage());
                        activity.runOnUiThread(() -> ToastUtils.show(activity, "服务器异常，请稍后再试"));
                        sendOnFailureMessage(callBack, call, new IOException());
                    }
                    if (response.body() != null) {
                        response.body().close();
                    }
                } else {
                    //主线程吐司
                    activity.runOnUiThread(() -> ToastUtils.show(activity, "服务器异常，请稍后再试"));
                    LogUtils.e("TAG", "************ FailureCode =" + response.code());
                    sendOnFailureMessage(callBack, call, new IOException());
                }
            }
        });
    }

    /**
     * 成功信息
     *
     * @param callBack
     * @param result
     */
    private void sendOnSuccessMessage(final BaseCallBack callBack, final Object result, final String msg) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess(result, msg);
            }
        });
    }

    /**
     * 失败信息
     *
     * @param callBack
     * @param call
     * @param e
     */
    private void sendOnFailureMessage(final BaseCallBack callBack, final Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onFailure(call, e);
            }
        });
    }

    /**
     * 错误信息
     *
     * @param callBack
     * @param code
     */
    private void sendOnErrorMessage(final BaseCallBack callBack, final int code, final String msg) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onError(code, msg);
            }
        });
    }
}
