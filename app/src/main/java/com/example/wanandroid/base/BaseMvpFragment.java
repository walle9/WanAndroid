package com.example.wanandroid.base;

import javax.inject.Inject;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.base
 * @ClassName: BaseMvpFragment
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/9/2 13:47
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/2 13:47
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment {
    @Inject
    protected T basePresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void initView() {
        initInject();
        if (null != basePresenter) {
            basePresenter.attachView(this);
        }

    }


    /**
     * 注入
     */
    public abstract void initInject();

    @Override
    public void onDestroy() {
        //解除V层和P层绑定,保证生命周期同步,防止泄露
        if (null != basePresenter) {
            basePresenter.detachView();
            basePresenter = null;
        }
        super.onDestroy();
    }

}
