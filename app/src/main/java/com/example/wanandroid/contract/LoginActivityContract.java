package com.example.wanandroid.contract;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.LoginBean;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.contract
 * @ClassName: LoginActivityContract
 * @Description: 登录控制层
 * @Author: walle
 * @CreateDate: 2019年10月23日00:50:36
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019年10月23日00:50:43
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface LoginActivityContract {

    interface IView extends BaseView{
        void loginSuccessful(LoginBean loginBean);
    }

    interface Presenter extends BasePresenter<IView> {
        void login(String username,String password);
    }
}
