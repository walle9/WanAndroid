package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BaseMvpPresenter;
import com.example.wanandroid.contract.FifthTabFragmentContract;
import com.example.wanandroid.contract.ForthTabFragmentContract;

import javax.inject.Inject;

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
public class FifthTabFragmentPresenter extends BaseMvpPresenter<FifthTabFragmentContract.IView> implements FifthTabFragmentContract.Presenter {

    @Inject
    FifthTabFragmentPresenter() {
    }

    @Override
    public void loadData(String city) {
        /*addSubscribe(dataHelper.weather(city)
                    .compose(MyRxUtils.ToMainHandlerHttpResultObservable(Schedulers.io()))
                    .doOnSubscribe(disposable -> baseView.showLoading())
                    .subscribeWith(new BaseDisposableObserver<WeatherBean>(baseView) {
                        @Override
                        public void onNext(WeatherBean weatherBean) {
                            baseView.showSuccessful(weatherBean.toString());
                        }
                    })
        );*/
    }
}
