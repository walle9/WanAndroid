package com.example.wanandroid.model.event;

/**
 * Created by walle9 on 2019/10/27.
 * 描述:
 */
public class LoginEvent {

    private boolean isLogin;

    public LoginEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
