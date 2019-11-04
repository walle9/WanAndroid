package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BaseMvpPresenter;
import com.example.wanandroid.contract.ArticleActivityContract;
import com.example.wanandroid.contract.FirstTabFragmentContract;
import com.example.wanandroid.model.bean.HomeBannerBean;
import com.example.wanandroid.model.bean.HomeListBean;
import com.example.wanandroid.model.http.BaseDisposableObserver;
import com.example.wanandroid.model.http.MyRxUtils;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

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
public class ArticleActivityPresenter extends BaseMvpPresenter<ArticleActivityContract.IView> implements ArticleActivityContract.Presenter {

    @Inject
    ArticleActivityPresenter() {
    }

    @Override
    public void collect(int id, ShineButton shineButton) {
        addSubscribe(dataHelper.collect(id)
        .compose(MyRxUtils.ToMainHandlerHttpNoResultObservable(Schedulers.io()))
                .subscribeWith(new BaseDisposableObserver<String>(baseView) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        baseView.showCollectFailed(shineButton);
                    }

                    @Override
                    public void onNext(String s) {
                        if (null!=baseView) {
                            baseView.showCollectSuccessful(s);
                        }
                    }
                })
        );
    }

    @Override
    public void uncollect(int id, ShineButton shineButton) {
            addSubscribe(dataHelper.uncollect(id)
                        .compose(MyRxUtils.ToMainHandlerHttpNoResultObservable(Schedulers.io()))
                        .subscribeWith(new BaseDisposableObserver<String>(baseView) {
                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                baseView.showUncollectFailed(shineButton);
                            }

                            @Override
                            public void onNext(String s) {
                                if (null!=baseView) {
                                    baseView.showUncollectSuccessful(s);
                                }
                            }
                        })
            );
    }

}
