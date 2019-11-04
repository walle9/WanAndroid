package com.example.wanandroid.base;

import com.example.wanandroid.model.http.api.DataHelper;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.base
 * @ClassName: BaseMvpPresenter
 * @Description: Presenter的基类
 * @Author: walle
 * @CreateDate: 2019/8/19 14:01
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 14:01
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class BaseMvpPresenter<T extends BaseView> implements BasePresenter<T> {

    @Inject
    protected DataHelper dataHelper;

    protected T baseView;
    private CompositeDisposable disposables;


    protected BaseMvpPresenter() {
        //也可直接在dataHelper上加@inject注解注入,BaseMvpPresenter被acticity所持有,对应的activiy也持有Presenter,他们的DataHelper是同一个
        //dataHelper = MyApplication.getAppComponent().getDataHelper();
    }

    @Override
    public void attachView(T baseView) {
        this.baseView = baseView;
    }

    @Override
    public void detachView() {
        this.baseView = null;
        unSubscribe();
    }


    /**
     * 添加disposable到容器
     *
     * @param disposable 订阅事件
     */
    protected void addSubscribe(Disposable disposable) {
        if (null == disposables) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
    }


    /**
     * 解除所有订阅者
     */
    private void unSubscribe() {
        if (null != disposables) {
            disposables.clear();
            disposables = null;
        }
    }
}
