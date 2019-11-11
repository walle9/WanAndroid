package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BaseMvpPresenter;
import com.example.wanandroid.contract.ArticleActivityContract;
import com.example.wanandroid.contract.SearchActivityContract;
import com.example.wanandroid.model.http.BaseDisposableObserver;
import com.example.wanandroid.model.http.MyRxUtils;
import com.sackcentury.shinebuttonlib.ShineButton;

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
public class SearchActivityPresenter extends BaseMvpPresenter<SearchActivityContract.IView> implements SearchActivityContract.Presenter {

    @Inject
    SearchActivityPresenter() {
    }

    @Override
    public void getHotKeyList() {
        // TODO: 2019/11/7
    }

    @Override
    public void queryArticle(int page, String key) {
        // TODO: 2019/11/7
    }
}
