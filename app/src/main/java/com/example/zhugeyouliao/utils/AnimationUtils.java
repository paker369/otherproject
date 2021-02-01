package com.example.zhugeyouliao.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/**
 * @Author： longyu
 * @Time： 2021/1/20 16:17
 * @description：  动画
 */
public class AnimationUtils {

    /**
     * 如果是从现在的位置移动到别的位置，那么初始XY的值都是0，向左边，那么结束X值就是 负数，向右则是正数，上下亦是如此
     * 如果是从别的位置移动到现在的位置，那么结束XY的值都是0，其他的和上面一样
     *
     * @param fromX 初始X位置
     * @param toX   结束X位置
     * @param fromY 初始Y位置
     * @param toY   结束Y位置
     * @param time  动画时间
     * @param view  控件
     */

    public static void setAnimation(float fromX, float toX, float fromY, float toY, long time, View view) {
        /**位移动画*/
        Animation mTranslateAnimation = new TranslateAnimation(fromX, toX, fromY, toY);
        mTranslateAnimation.setDuration(time);
        /** 组合动画*/
        AnimationSet mAnimationSet = new AnimationSet(false);
        mAnimationSet.setFillAfter(true);//true 设置动画结束后，控件位置保持不动，false 则是返回初始位置
        mAnimationSet.addAnimation(mTranslateAnimation);
        view.startAnimation(mAnimationSet);
    }
}
