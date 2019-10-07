package com.example.wanandroid.base;

import androidx.annotation.StringRes;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.base
 * @ClassName: BaseView
 * @Description: 所有V层的基类
 * @Author: walle
 * @CreateDate: 2019/8/16 11:28
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/16 11:28
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface BaseView {

    /**
     * 提示,交由子类实现
     *
     * @param msg 要提示的字符串
     */
    void showTipMsg(String msg);

    /**
     * 提示,交由子类实现
     *
     * @param msg 提示内容res目录下面的String的int
     */
    void showTipMsg(@StringRes int msg);

    /**
     * 网络请求的时候显示正在加载的对话框
     */
    void showLoading();

    /**
     * 网络请求完成时隐藏加载对话框
     */
    void hideLoading();

    /**
     * 用于检测你当前用户的token是否有效
     */
    void invalidToken();

    /**
     * Finish当前页面
     */
    void myFinish();


    /**
     * 处理请求出错逻辑
     */
    void ShowError();
}
