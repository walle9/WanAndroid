package com.example.wanandroid.base;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.base
 * @ClassName: BasePresenter
 * @Description: 不带mvp的presenter的基类接口
 * @Author: walle
 * @CreateDate: 2019/8/19 13:56
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 13:56
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface BasePresenter<T extends BaseView> {

    /**
     * 绑定View
     * @param baseView  view
     */
    void attachView(T baseView);

    /**
     * 解除绑定View,保证P层的生命周期和V层同步,避免内存泄露
     */
    void detachView();
}
