package com.example.wanandroid.utils;

import android.content.Context;
import android.view.Gravity;

import com.hjq.toast.style.BaseToastStyle;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.utils
 * @ClassName: MyToastStyle
 * @Description: Toast样式
 * @Author: walle
 * @CreateDate: 2019/10/28 15:17
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/10/28 15:17
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyToastStyle extends BaseToastStyle {
    public MyToastStyle(Context context) {
        super(context);
    }

    @Override
    public int getGravity() {
        return Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM;
    }

    @Override
    public int getYOffset() {
        return dp2px(100);
    }

    @Override
    public int getCornerRadius() {
        return dp2px(25);
    }

    @Override
    public int getBackgroundColor() {
        return 0XFFFF80AB;
    }

    @Override
    public int getTextColor() {
        return 0XFFFFFFFF;
    }

    @Override
    public float getTextSize() {
        return sp2px(14);
    }

    @Override
    public int getPaddingStart() {
        return dp2px(16);
    }

    @Override
    public int getPaddingTop() {
        return dp2px(10);
    }
}
