package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BaseMvpPresenter;
import com.example.wanandroid.contract.SecondTabFragmentContract;
import com.example.wanandroid.model.bean.WeatherBean;
import com.example.wanandroid.model.http.BaseDisposableObserver;
import com.example.wanandroid.model.http.MyRxUtils;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.presenter
 * @ClassName: WeatherActivityPresenter
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/9/2 20:52
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/2 20:52
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class SecondTabFragmentPresenter extends BaseMvpPresenter<SecondTabFragmentContract.IView> implements SecondTabFragmentContract.Presenter {

    @Inject
    SecondTabFragmentPresenter() {
    }


    @Override
    public void getGirlList(String count, String page) {

    }
}
