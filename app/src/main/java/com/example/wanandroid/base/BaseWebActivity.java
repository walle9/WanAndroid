package com.example.wanandroid.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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
import com.orhanobut.logger.Logger;

import java.util.Objects;

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
public abstract class BaseWebActivity extends BaseActivity {

    protected AgentWeb mAgentWeb;

    protected MiddlewareWebChromeBase mMiddleWareWebChrome;
    protected MiddlewareWebClientBase mMiddleWareWebClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildAgentWeb();
    }

    protected void buildAgentWeb() {

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(getAgentWebParent(), new ViewGroup.LayoutParams(-1, -1))
                .useDefaultIndicator(getIndicatorColor(), getIndicatorHeight())
                .setWebChromeClient(getWebChromeClient())
                .setWebViewClient(getWebViewClient())
                .setWebView(getWebView())
                .setPermissionInterceptor(getPermissionInterceptor())
                .setWebLayout(getWebLayout())
                .setAgentWebUIController(getAgentWebUIController())
                .interceptUnkownUrl()
                .setOpenOtherPageWays(getOpenOtherAppWay())
                .useMiddlewareWebChrome(getMiddleWareWebChrome())
                .useMiddlewareWebClient(getMiddleWareWebClient())
                .setAgentWebWebSettings(getAgentWebSettings())
                .setMainFrameErrorView(getErrorLayout(), getErrorClickViewId())
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()
                .ready()
                .go(getUrl());
    }


    /**
     * 获取MiddleWareWebClient
     *
     * @return MiddlewareWebClientBase
     */
    protected @NonNull
    MiddlewareWebClientBase getMiddleWareWebClient() {
        return this.mMiddleWareWebClient = new MiddlewareWebClientBase() {
        };
    }


    /**
     * 获取MiddleWareWebChrome
     *
     * @return MiddlewareWebChromeBase
     */
    protected @NonNull
    MiddlewareWebChromeBase getMiddleWareWebChrome() {
        return this.mMiddleWareWebChrome = new MiddlewareWebChromeBase() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        };
    }


    /**
     * 打开其他网页的方式
     *
     * @return DefaultWebClient.OpenOtherPageWays
     */
    public @Nullable
    DefaultWebClient.OpenOtherPageWays getOpenOtherAppWay() {
        return null;
    }


    /**
     * 获取错误页面点击id,默认-1
     *
     * @return id
     */
    protected int getErrorClickViewId() {
        return -1;
    }


    /**
     * 获取错误页面
     *
     * @return 错误页面id
     */
    protected int getErrorLayout() {
        return R.layout.empty_view;
    }


    /**
     * 设置代理
     *
     * @return AgentWebUIControllerImplBase
     */
    protected @Nullable
    AgentWebUIControllerImplBase getAgentWebUIController() {
        return null;
    }


    /**
     * 获取WebChromeClient
     *
     * @return WebChromeClient
     */
    protected @Nullable
    WebChromeClient getWebChromeClient() {
        return null;
    }


    /**
     * 获取权限拦截
     *
     * @return PermissionInterceptor
     */
    protected @Nullable
    PermissionInterceptor getPermissionInterceptor() {
        return null;
    }


    /**
     * 获取settings
     *
     * @return IAgentWebSettings
     */
    protected @Nullable
    IAgentWebSettings getAgentWebSettings() {
        return AgentWebSettingsImpl.getInstance();
    }


    /**
     * 获取webView
     *
     * @return IWebLayout
     */
    protected @Nullable
    IWebLayout getWebLayout() {
        return null;
    }


    /**
     * 获取webView
     *
     * @return WebView
     */
    protected @Nullable
    WebView getWebView() {
        return null;
    }

    /**
     * 获取自定义进度条
     *
     * @return BaseIndicatorView
     */
    protected BaseIndicatorView getIndicatorView() {
        return null;

    }

    /**
     * 获取WebViewClient
     *
     * @return WebViewClient
     */
    protected @Nullable
    WebViewClient getWebViewClient() {
        return null;
    }


    /**
     * 获取默认加载进度条颜色,默认-1(绿色)
     *
     * @return 进度条颜色
     */
    protected @ColorInt
    int getIndicatorColor() {
        return -1;
    }


    /**
     * 获取默认加载进度条高度,默认1dp
     *
     * @return 进度条高度(dp)
     */
    protected int getIndicatorHeight() {
        return 1;
    }


    @Override
    protected void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();

        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




    //---------------------------抽象方法,交由子类实现---------------------------

    /**
     * 获取url
     *
     * @return string url
     */
    protected abstract @Nullable
    String getUrl();

    /**
     * 获取webView加载的根布局
     *
     * @return ViewGroup
     */
    protected abstract @NonNull
    ViewGroup getAgentWebParent();

    /**
     * 设置标题
     *
     * @param title title
     */
    protected void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    protected boolean isSetNavigationOnClickListener() {
        return false;
    }
}
