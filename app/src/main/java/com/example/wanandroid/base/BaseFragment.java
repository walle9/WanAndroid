package com.example.wanandroid.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.di.component.ActivityComponent;
import com.example.wanandroid.di.component.DaggerActivityComponent;
import com.example.wanandroid.di.module.ActivityModule;
import com.example.wanandroid.utils.ToastUtils;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.base
 * @ClassName: BaseFragment
 * @Description: 所有fragment的基类, 不使用mvp的fragment可直接继承本类
 * @Author: walle
 * @CreateDate: 2019/8/19 10:16
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 10:16
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseFragment extends SwipeBackFragment implements BaseView, View.OnClickListener {

    private boolean isInit;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutRes = layoutRes();
        View view;

        if (0 != layoutRes) {
            view = inflater.inflate(layoutRes, null);
        } else {
            view = super.onCreateView(inflater, container, savedInstanceState);
        }

        //设置是否可以滑动返回,默认不可以
        if (swipeBackEnable()) {
            view = attachToSwipeBack(view);
        }
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInit = true;
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化控件
     */
    protected abstract void initView();


    protected <T extends View> T findViewById(@IdRes int id) {
        return rootView.findViewById(id);
    }


    /**
     * 设置点击事件.
     *
     * @param ids 被点击View的ID
     * @return {@link BaseFragment}
     */
    public BaseFragment setOnClick(@IdRes int... ids) {
        for (int id : ids) {
            rootView.findViewById(id).setOnClickListener(this);
        }
        return this;
    }


    /**
     * 设置点击事件.
     *
     * @param views 被点击View的ID
     * @return {@link BaseFragment}
     */
    public BaseFragment setOnClick(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
        return this;
    }


    @Override
    public void onDestroy() {
        rootView = null;
        super.onDestroy();
    }

    /**
     * 获取布局资源
     *
     * @return 布局资源
     */
    protected abstract int layoutRes();

    @Override
    public void onClick(View v) {
    }

    @Override
    public void showTipMsg(String msg) {
        ToastUtils.showTipMsg(msg);
    }

    @Override
    public void showTipMsg(int msg) {
        ToastUtils.showTipMsg(msg);
    }

    @Override
    public void showLoading() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity instanceof BaseMvpActivity) {
            activity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity instanceof BaseMvpActivity) {
            activity.hideLoading();
        }
    }

    @Override
    public void invalidToken() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity instanceof BaseMvpActivity) {
            activity.invalidToken();
        }
    }

    @Override
    public void myFinish() {
        onBackPressedSupport();
    }


    /**
     * 设置是否可以滑动退出,默认不可以
     *
     * @return true, 可以
     */
    private boolean swipeBackEnable() {
        return false;
    }


    /**
     * 获取Activity 注射器
     * @return
     */
    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .activityModule(new ActivityModule())
                .build();
    }


}
