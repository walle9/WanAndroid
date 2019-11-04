package com.example.wanandroid.model.http;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.base.MyApplication;
import com.orhanobut.logger.Logger;

import io.reactivex.observers.DisposableObserver;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http
 * @ClassName: BaseDisposableObserver
 * @Description: 自定义DisposableObserver
 * @Author: walle
 * @CreateDate: 2019/8/19 20:52
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 20:52
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseDisposableObserver<T> extends DisposableObserver<T> {

    private BaseView baseView;

    public BaseDisposableObserver(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onError(Throwable e) {
        if (null == baseView) {
            return;
        }
        baseView.hideLoading();
        ApiException apiException = FactoryException.analysisExcetpion(e);
        baseView.showTipMsg(MyApplication.getInstance().getResources().getString(R.string.code_tip, apiException.getCode(), apiException.getMessage()));
    }

    @Override
    public void onComplete() {
        if (null != baseView) {
            baseView.hideLoading();
        }
    }

}
