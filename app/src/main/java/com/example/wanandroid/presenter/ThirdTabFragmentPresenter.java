package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BaseMvpPresenter;
import com.example.wanandroid.contract.ThirdTabFragmentContract;
import com.example.wanandroid.model.bean.LookerBean;
import com.example.wanandroid.model.http.BaseDisposableObserver;
import com.example.wanandroid.model.http.MyRxUtils;

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
public class ThirdTabFragmentPresenter extends BaseMvpPresenter<ThirdTabFragmentContract.IView> implements ThirdTabFragmentContract.Presenter {

    @Inject
    ThirdTabFragmentPresenter() {
    }


    private int page = 1;

    @Override
    public void getGirlList(int count, boolean isRefresh) {
        if (isRefresh) {
            page = 1;
        }

        addSubscribe(dataHelper.getGirlList(count, page)
                .compose(MyRxUtils.ToMainHandlerHttpResultObservable(Schedulers.io()))
                .doOnSubscribe(disposable -> baseView.showLoading())
                .subscribeWith(new BaseDisposableObserver<List<LookerBean>>(baseView) {
                    @Override
                    public void onNext(List<LookerBean> lookerBeans) {

                        if (null != baseView) {
                            if (null != lookerBeans) {
                                baseView.showSuccessful(lookerBeans, isRefresh);
                                page++;
                            } else {
                                baseView.showHasNoData(isRefresh);
                            }
                        }
                    }
                })
        );

    }
}
