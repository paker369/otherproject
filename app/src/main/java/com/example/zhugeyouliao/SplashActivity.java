package com.example.zhugeyouliao;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.example.zhugeyouliao.base.BaseActivity;
import com.example.zhugeyouliao.utils.ImageUtils;
import com.example.zhugeyouliao.utils.ScreenUtils;
import com.example.zhugeyouliao.utils.StringUtils;
import com.example.zhugeyouliao.widget.CircleProgressbar;


/**
 * 闪屏页面
 */
public class SplashActivity extends BaseActivity {

    private ImageView mIvSplash;

    private CircleProgressbar mCircleProgressbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        mIvSplash = findViewById(R.id.iv_splash);
        mCircleProgressbar = findViewById(R.id.circle_progress_bar);

        initCircleProgressBar();

        //TODO 获取广告图片地址 和 时长
        String img_path = "";
        long time_length = 5000;
        if (!StringUtils.isEmpty(img_path)) {
            //显示广告图片
            Bitmap imageThumbnail = ImageUtils.getImageThumbnail(img_path,
                    ScreenUtils.getScreenWidth(mContext), ScreenUtils.getScreenHeight(mContext));
            if (imageThumbnail != null && mIvSplash != null) {
                mIvSplash.setImageBitmap(imageThumbnail);
            }
            //显示倒计时
            mCircleProgressbar.setVisibility(View.VISIBLE);
            mCircleProgressbar.setTimeMillis(time_length);
            mCircleProgressbar.reStart();
        } else {
            //没有广告直接跳走
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toMainPager();
                }
            }, 2000);
        }
    }

    //跳转首页
    private void toMainPager() {
            MyApplication.openActivity(this, MainActivity.class);
            finish();
    }

    //初始化圆形进度条
    private void initCircleProgressBar() {
        mCircleProgressbar.setOutLineColor(Color.WHITE);
        mCircleProgressbar.setInCircleColor(Color.parseColor("#505559"));
        mCircleProgressbar.setProgressColor(Color.parseColor("#1BB079"));
        mCircleProgressbar.setProgressLineWidth(4);
        mCircleProgressbar.setProgressType(CircleProgressbar.ProgressType.COUNT);

        //倒计时结束
        mCircleProgressbar.setCountdownProgressListener(1, new CircleProgressbar.OnCountdownProgressListener() {
            @Override
            public void onProgress(int what, int progress) {
                if (what == 1 && progress == 100) {
                    toMainPager();
                }
            }
        });

        //跳过
        mCircleProgressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleProgressbar.stop();
                toMainPager();
            }
        });
    }

    @Override
    protected boolean needStatusBarDarkText() {
        return true;
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.transparent;
    }
}
