package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BaseMvpPresenter;
import com.example.wanandroid.contract.LookerFragmentContract;

import javax.inject.Inject;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.presenter
 * @ClassName: LookerFragmentPresenter
 * @Description: 获取美女图片,P层
 * @Author: walle
 * @CreateDate: 2019/9/4 17:40
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/4 17:40
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LookerFragmentPresenter extends BaseMvpPresenter<LookerFragmentContract.IView> implements LookerFragmentContract.Presenter {

    private int currentPage = 1;

    @Inject
    public LookerFragmentPresenter() {
    }

    @Override
    public void loadData(boolean isRefresh, String count) {
        if (isRefresh) {
            currentPage= 1;
        }

        /*addSubscribe(dataHelper.LookerImgs()
                    .compose(MyRxUtils.ToMainHandlerHttpResultObservable(Schedulers.io()))
                    .subscribeWith(new BaseDisposableObserver<List<LookerBean>>(baseView) {
                        @Override
                        public void onNext(List<LookerBean> lookerBeans) {
                            baseView.showSuccessful(lookerBeans);
                        }
                    })
        );*/
    }
}
