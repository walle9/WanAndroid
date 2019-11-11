package com.example.wanandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseMvpActivity;
import com.example.wanandroid.base.BaseMvpWebActivity;
import com.example.wanandroid.contract.SearchActivityContract;
import com.example.wanandroid.model.bean.HomeListBean;
import com.example.wanandroid.model.bean.HotKeyBean;
import com.example.wanandroid.presenter.SearchActivityPresenter;

import java.util.List;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.ui.activity
 * @ClassName: SearchActivity
 * @Description: 文章搜索
 * @Author: walle
 * @CreateDate: 2019/11/6 15:21
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/11/6 15:21
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class SearchActivity extends BaseMvpActivity<SearchActivityPresenter> implements SearchActivityContract.IView {


    public static void actionStartActivity(ContextThemeWrapper context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showGetHotKeySuccessful(List<HotKeyBean> hotKeyBeans) {

    }

    @Override
    public void showGetHotKeyFailed() {

    }

    @Override
    public void showQueryArticleSuccessful(HomeListBean homeListBean) {

    }

    @Override
    public void showQueryArticleFailed() {

    }

    @Override
    public boolean isShowToolbar() {
        return true;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        return true;
    }
}
