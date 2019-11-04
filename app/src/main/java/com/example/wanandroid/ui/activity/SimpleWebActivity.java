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

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseWebActivity;
import com.example.wanandroid.utils.CustomSettings;
import com.example.wanandroid.utils.SystemUtil;
import com.just.agentweb.IAgentWebSettings;
import com.sackcentury.shinebuttonlib.ShineButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.view.activity
 * @ClassName: SimpleWebActivity
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/10/15 22:15
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/10/15 22:15
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class SimpleWebActivity extends BaseWebActivity {


    private LinearLayout mLlWebContainer;

    public static void actionStartActivity(ContextThemeWrapper context, String title, String url) {
        Intent intent = new Intent(context, SimpleWebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
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
    protected void initView() {
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
                // TODO: 2019/10/16 收藏
                showTipMsg("收藏被点击了");
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
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean isShowToolbar() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        MenuItem item = menu.findItem(R.id.menu_web_collect);
        View actionView = item.getActionView();
        ShineButton shineButton = actionView.findViewById(R.id.shine_button);
        shineButton.init(this);
        actionView.setOnClickListener(view -> onOptionsItemSelected(item));
        return true;
    }

    @Nullable
    @Override
    protected IAgentWebSettings getAgentWebSettings() {
        return new CustomSettings();
    }

    @Override
    public boolean swipeEnable() {
        return true;
    }
}
