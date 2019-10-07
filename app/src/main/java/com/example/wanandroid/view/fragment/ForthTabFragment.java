package com.example.wanandroid.view.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseMvpFragment;
import com.example.wanandroid.base.MyApplication;
import com.example.wanandroid.contract.ForthTabFragmentContract;
import com.example.wanandroid.presenter.ForthTabFragmentPresenter;

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
public class ForthTabFragment extends BaseMvpFragment<ForthTabFragmentPresenter> implements ForthTabFragmentContract.IView {

    private TextView mTvFragmentContent;

    public static SupportFragment newInstance() {
        Bundle args = new Bundle();
        ForthTabFragment fragment = new ForthTabFragment();
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
    }

    @Override
    public void ShowError() {

    }
}
