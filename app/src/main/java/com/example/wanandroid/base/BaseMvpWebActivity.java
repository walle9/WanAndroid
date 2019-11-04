package com.example.wanandroid.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.R;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebSettingsImpl;
import com.just.agentweb.AgentWebUIControllerImplBase;
import com.just.agentweb.BaseIndicatorView;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.IAgentWebSettings;
import com.just.agentweb.IWebLayout;
import com.just.agentweb.MiddlewareWebChromeBase;
import com.just.agentweb.MiddlewareWebClientBase;
import com.just.agentweb.PermissionInterceptor;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;

import javax.inject.Inject;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.view.activity
 * @ClassName: BaseWebActivity
 * @Description: webView基类
 * @Author: walle
 * @CreateDate: 2019/10/11 15:39
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/10/11 15:39
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseMvpWebActivity<T extends BasePresenter> extends BaseWebActivity {

    @Inject
    protected T basePresenter;

    @Override
    @SuppressWarnings("unchecked")
    protected void initView() {
        initInject();
        if (null != basePresenter) {
            basePresenter.attachView(this);
        }
    }

    /**
     * 注入
     */
    protected abstract void initInject();


    @Override
    protected void onDestroy() {

        //解除V层和P层绑定,保证生命周期同步,防止泄露
        if (null != basePresenter) {
            basePresenter.detachView();
            basePresenter = null;
        }

        super.onDestroy();
    }
}
