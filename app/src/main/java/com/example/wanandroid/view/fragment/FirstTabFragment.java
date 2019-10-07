package com.example.wanandroid.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseMvpFragment;
import com.example.wanandroid.contract.FirstTabFragmentContract;
import com.example.wanandroid.presenter.FirstTabFragmentPresenter;
import com.example.wanandroid.view.activity.WeatherActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.view.fragment
 * @ClassName: FirstTabFragment
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/9/3 10:15
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/3 10:15
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FirstTabFragment extends BaseMvpFragment<FirstTabFragmentPresenter> implements FirstTabFragmentContract.IView {

    private TextView mTvFragmentContent;

    public static SupportFragment newInstance() {
        Bundle args = new Bundle();
        FirstTabFragment fragment = new FirstTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment;
    }

    @Override
    public void showSuccessful(String msg) {
        mTvFragmentContent.setText(msg);
    }

    @Override
    protected void initData() {
        basePresenter.loadData(_mActivity.getResources().getString(R.string.city_bengbu));
    }

    @Override
    protected void initView() {
        super.initView();
        mTvFragmentContent = findViewById(R.id.tv_fragment_content);
        Button btn = findViewById(R.id.btn_fragment);
        setOnClick(btn);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_fragment:
                startActivity(new Intent(_mActivity, WeatherActivity.class));
                break;
            default:
                break;
        }

    }

    @Override
    public void ShowError() {

    }
}
