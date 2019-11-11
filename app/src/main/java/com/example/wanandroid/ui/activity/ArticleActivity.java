package com.example.wanandroid.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseMvpWebActivity;
import com.example.wanandroid.contract.ArticleActivityContract;
import com.example.wanandroid.model.event.CollectionEvent;
import com.example.wanandroid.model.event.LoginEvent;
import com.example.wanandroid.presenter.ArticleActivityPresenter;
import com.example.wanandroid.utils.Constants;
import com.example.wanandroid.utils.CustomSettings;
import com.example.wanandroid.utils.SPUtils;
import com.example.wanandroid.utils.SystemUtil;
import com.hjq.toast.ToastUtils;
import com.just.agentweb.IAgentWebSettings;
import com.orhanobut.logger.Logger;
import com.sackcentury.shinebuttonlib.ShineButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.ui.activity
 * @ClassName: ArticleActivity
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/10/30 19:21
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/10/30 19:21
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ArticleActivity extends BaseMvpWebActivity<ArticleActivityPresenter> implements ArticleActivityContract.IView {

    private LinearLayout mLlWebContainer;
    private int mArticleId;
    private ShineButton mShineButton;

    public static void actionStartActivity(ContextThemeWrapper context, boolean isBanner,boolean isCollect,int articleId, String title, String url) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra("isBanner", isBanner);
        intent.putExtra("isCollect", isCollect);
        intent.putExtra("articleId", articleId);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        mLlWebContainer = findViewById(R.id.ll_web_container);
        toolbar.inflateMenu(R.menu.menu_web);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!mAgentWeb.back()){
                    myFinish();
                }
                break;
            case R.id.menu_web_collect:
               setCollect(mShineButton,mArticleId);
                break;

            case R.id.menu_web_copy:
                SystemUtil.copyToClipBoard(this, mAgentWeb.getWebCreator().getWebView().getUrl());
                break;

            case R.id.menu_web_open_in_browser:
                if (mAgentWeb != null) {
                    openBrowser(mAgentWeb.getWebCreator().getWebView().getUrl());
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setCollect(ShineButton view, int articleId) {

        if (SPUtils.newInstance(Constants.SP_NAME_USERINFO).get(Constants.KEY_USERINFO_ISLOGIN,
                false)) {
            if (view.isChecked()) {
                //点击状态先变成选中了
                basePresenter.collect(articleId, view);
            } else {
                basePresenter.uncollect(articleId, view);
            }

        } else {
            LoginActivity.actionStartActivity(this);
            if (view.isChecked()) {
                //还原点击前的状态
                view.setChecked(false);
            } else {
                view.setChecked(true);
            }
        }
    }

    private void openBrowser(String targetUrl) {
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            showTipMsg(targetUrl + getResources().getString(R.string.open_browser_error));
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri mUri = Uri.parse(targetUrl);
        intent.setData(mUri);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean isBanner = getIntent().getBooleanExtra("isBanner", false);
        getMenuInflater().inflate(R.menu.menu_web, menu);
        if (!isBanner) {
            MenuItem item = menu.findItem(R.id.menu_web_collect);
            View actionView = item.getActionView();
            if (actionView instanceof SearchView) {
                // TODO: 2019/11/7  
            }
            mShineButton = actionView.findViewById(R.id.shine_button);
            mShineButton.init(this);
            boolean isCollect = getIntent().getBooleanExtra("isCollect", false);
            mShineButton.setChecked(isCollect);
            mShineButton.setOnClickListener(view -> onOptionsItemSelected(item));
        }
        return true;
    }

    @Nullable
    @Override
    protected IAgentWebSettings getAgentWebSettings() {
        return new CustomSettings();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Nullable
    @Override
    protected String getUrl() {
        return getIntent().getStringExtra("url");
    }

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return mLlWebContainer;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_web;
    }

    @Override
    protected void initData() {
        mArticleId = getIntent().getIntExtra("articleId", -1);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean isShowToolbar() {
        return true;
    }

    @Override
    public boolean swipeEnable() {
        return true;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void showCollectSuccessful(String msg) {
        if (!mShineButton.isChecked()) {
            mShineButton.setChecked(true);
        }
        //通知文章列表刷新
        EventBus.getDefault().post(new CollectionEvent(true,mArticleId));
    }

    @Override
    public void showCollectFailed(ShineButton shineButton) {
            if (shineButton.isChecked()) {
                shineButton.setChecked(false);
            }
    }

    @Override
    public void showUncollectSuccessful(String msg) {
        if (mShineButton.isChecked()) {
            mShineButton.setChecked(false);
        }
        //通知文章列表刷新
        EventBus.getDefault().post(new CollectionEvent(false,mArticleId));

    }

    @Override
    public void showUncollectFailed(ShineButton shineButton) {
        if (!shineButton.isChecked()) {
            shineButton.setChecked(true);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent loginEvent) {
        if (mAgentWeb != null&&loginEvent.isLogin()&&mArticleId!=-1) {
            basePresenter.collect(mArticleId,mShineButton);
        }
    }
}
