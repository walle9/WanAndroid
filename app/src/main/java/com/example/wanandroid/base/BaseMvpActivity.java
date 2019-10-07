package com.example.wanandroid.base;

import javax.inject.Inject;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.base
 * @ClassName: BaseMvpActivity
 * @Description: 所有使用mvp模式的Activity的父类
 * @Author: walle
 * @CreateDate: 2019/8/19 14:09
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 14:09
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity {
    @Inject
    protected T basePresenter;

    @Override
    @SuppressWarnings("unchecked")
    protected void initView() {
        initInject();
        if (null != basePresenter) {
            basePresenter.attachView(this);
        }
    }

    /**
     * 注入
     */
    protected abstract void initInject();


    @Override
    protected void onDestroy() {

        //解除V层和P层绑定,保证生命周期同步,防止泄露
        if (null != basePresenter) {
            basePresenter.detachView();
            basePresenter = null;
        }

        super.onDestroy();
    }

}
