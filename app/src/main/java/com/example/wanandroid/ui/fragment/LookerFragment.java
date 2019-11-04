package com.example.wanandroid.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseMvpFragment;
import com.example.wanandroid.contract.LookerFragmentContract;
import com.example.wanandroid.model.bean.LookerBean;
import com.example.wanandroid.presenter.LookerFragmentPresenter;
import com.example.wanandroid.utils.UIUtils;

import java.util.List;

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
public class LookerFragment extends BaseMvpFragment<LookerFragmentPresenter> implements LookerFragmentContract.IView {

    private TextView mTvFragmentContent;

    public static SupportFragment newInstance() {
        Bundle args = new Bundle();
        LookerFragment fragment = new LookerFragment();
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
    protected void initData() {
        basePresenter.loadData(true,_mActivity.getString(R.string.looker_count));
    }

    @Override
    protected void initView() {
        super.initView();
        mTvFragmentContent = findViewById(R.id.tv_fragment_content);
        Button btn = findViewById(R.id.btn_fragment);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    @Override
    public void showSuccessful(List<LookerBean> lookerBeans) {
        UIUtils.showToast("请求成功");
    }

}
