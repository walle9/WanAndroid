package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BaseMvpPresenter;
import com.example.wanandroid.contract.LoginActivityContract;
import com.example.wanandroid.model.bean.LoginBean;
import com.example.wanandroid.model.http.BaseDisposableObserver;
import com.example.wanandroid.model.http.MyRxUtils;
import com.example.wanandroid.utils.Constants;
import com.example.wanandroid.utils.SPUtils;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.presenter
 * @ClassName: LoginActivityPresenter
 * @Description: 登录Presenter
 * @Author: walle
 * @CreateDate: 2019年10月23日01:07:05
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019年10月23日01:07:09
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LoginActivityPresenter extends BaseMvpPresenter<LoginActivityContract.IView> implements LoginActivityContract.Presenter {

    @Inject
    LoginActivityPresenter() {
    }

    @Override
    public void login(String username, String password) {
        addSubscribe(dataHelper.login(username, password)
                .compose(MyRxUtils.ToMainHandlerHttpResultObservable(Schedulers.io()))
                .doOnSubscribe(disposable -> baseView.showLoading())
                .subscribeWith(new BaseDisposableObserver<LoginBean>(baseView) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (null != baseView) {
                            baseView.hideLoading();
                            baseView.loginSuccessful(loginBean);
                            saveUserInfo(loginBean);
                        }
                    }
                }));
    }

    private void saveUserInfo(LoginBean loginBean) {
        SPUtils.newInstance(Constants.SP_NAME_USERINFO).save(Constants.KEY_USERINFO_ISLOGIN,true);
    }
}
