package com.example.wanandroid.utils;

import android.widget.Toast;

import androidx.annotation.StringRes;

import com.example.wanandroid.base.MyApplication;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.utils
 * @ClassName: ToastUtils
 * @Description: Toast工具类
 * @Author: walle
 * @CreateDate: 2019/8/16 11:57
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/16 11:57
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ToastUtils {

    private static Toast toast;

    private ToastUtils() {
        throw new RuntimeException("工具类不允许创建对象");
    }

    @SuppressWarnings("all")
    private static void init() {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(), "", Toast.LENGTH_SHORT);
        }
    }

    public static void showTipMsg(String msg) {
        if (null == toast) {
            init();
        }
        toast.setText(msg);
        toast.show();
    }

    public static void showTipMsg(@StringRes int msg) {
        if (null == toast) {
            init();
        }
        toast.setText(msg);
        toast.show();
    }

}
