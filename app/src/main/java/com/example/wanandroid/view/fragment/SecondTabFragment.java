package com.example.wanandroid.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseMvpFragment;
import com.example.wanandroid.base.MyApplication;
import com.example.wanandroid.contract.SecondTabFragmentContract;
import com.example.wanandroid.presenter.SecondTabFragmentPresenter;

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
public class SecondTabFragment extends BaseMvpFragment<SecondTabFragmentPresenter> implements SecondTabFragmentContract.IView {

    private TextView mTvFragmentContent;

    public static SupportFragment newInstance() {
        Bundle args = new Bundle();
        SecondTabFragment fragment = new SecondTabFragment();
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
        /*basePresenter.loadData();*/
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
                start(LookerFragment.newInstance());
                break;
            default:
                break;
        }
    }

    @Override
    public void ShowError() {

    }
}
