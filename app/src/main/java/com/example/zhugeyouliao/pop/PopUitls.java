package com.example.zhugeyouliao.pop;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.utils.DensityUtil;
import com.example.zhugeyouliao.utils.ScreenUtils;
import com.example.zhugeyouliao.utils.StatusBarUtils;

/**
 * @ClassName: PopUitls
 * @Description: popWindow工具类
 * @Author: longyu
 * @CreateDate: 2019/12/23 0023 17:17
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class PopUitls {

    private static PopUitls mInstance;

    private Activity mContext;

    private PopupWindow mPopupWindow;

    private OnSureClickListener mOnSureClickListener;

    public void setOnSureClickListener(OnSureClickListener mOnSureClickListener) {
        this.mOnSureClickListener = mOnSureClickListener;
    }

    public interface OnSureClickListener {
        void onSureClick();
    }

    private PopUitls(Activity context) {
        this.mContext = context;
    }

    /**
     * 单例
     *
     * @return
     */
    public static synchronized PopUitls getInstance(Activity context) {
        if (mInstance == null) {
            mInstance = new PopUitls(context);
        }
        return mInstance;
    }

    /**
     * @param conent   内容
     * @return
     * @description 初始化
     * @date: 2019/12/23 0023 17:21
     * @author: wangfeilong
     */
    public void initPopWindow(String conent) {
        View cleanCachePopView = mContext.getLayoutInflater().inflate(R.layout.pop_layout, null);
        TextView tvCancle = cleanCachePopView.findViewById(R.id.tv_cancel);
        TextView tvContent = cleanCachePopView.findViewById(R.id.tv_pop_content);
        TextView tvSure = cleanCachePopView.findViewById(R.id.tv_sure);
        tvContent.setText(conent);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (mOnSureClickListener != null) {
                    mOnSureClickListener.onSureClick();
                }
            }
        });

        mPopupWindow = new PopupWindow(cleanCachePopView, ScreenUtils.getScreenWidth(mContext) - DensityUtil.dip2px(mContext, 35), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(drawable));
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Window window = mContext.getWindow();
                WindowManager.LayoutParams wl = window.getAttributes();
                wl.alpha = 1f;
                window.setAttributes(wl);
            }
        });
    }

    /**
     * @param
     * @return
     * @description 显示
     * @date: 2019/12/23 0023 17:24
     * @author: wangfeilong
     */
    public void showPopWindow(View view) {
        Window window = mContext.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.alpha = 0.7f;
        window.setAttributes(wl);
        mPopupWindow.setAnimationStyle(R.style.pop_animation_center);
        mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    /**
     * 关闭
     */
    public void dismiss() {
        mPopupWindow.dismiss();
    }

     /**
      * @date: 2021/1/20 18:44
      * @description  销毁
      */
    public void onDestroy() {
        mPopupWindow = null;
        mInstance = null;
    }

}
