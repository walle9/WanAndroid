package com.example.wanandroid.base;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.wanandroid.R;
import com.example.wanandroid.di.component.ActivityComponent;
import com.example.wanandroid.di.component.DaggerActivityComponent;
import com.example.wanandroid.di.module.ActivityModule;
import com.hjq.toast.ToastUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.base
 * @ClassName: BaseActivity
 * @Description: 所有Activity的父类, 不使用mvp模式的activity可直接继承本类
 * @Author: walle
 * @CreateDate: 2019/8/16 11:27
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/16 11:27
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseActivity extends SwipeBackActivity implements BaseView, View.OnClickListener {

    private AlertDialog loadingDialog;
    protected Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置是否可以滑动返回,默认不可滑动
        setSwipeBackEnable(swipeEnable());
        //设置是否强制竖屏,默认强制
        setRequestedOrientation(orientationPortraitEnable());
        //设置是否注册eventBus,默认不注册
        setEventBusEnable(isRegisterEventBus());

        //设置布局
        initLayout(savedInstanceState);
        //初始化toolbar和toolbar和具体布局的分割线
        initToolbarAndSplitLine();
        //初始化用户的控件
        initView();
        //初始化数据
        initData();

        MyApplication.getAppComponent().getAppManager().addActivity(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getAppComponent().getAppManager().finishActivity(this);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    //--------------------------------------子类必须实现的方法----------------------------------

    /**
     * 获取当前布局对象,交由子类实现
     *
     * @param savedInstanceState 这个是当前activity保存的数据，最常见的就是横竖屏切换的时候，
     *                           数据丢失问题
     * @return 当前布局的int值
     */
    protected abstract int getLayoutId(Bundle savedInstanceState);

    /**
     * 初始化布局,交由子类实现
     */
    protected abstract void initView();

    /**
     * 加载数据的方法,交由子类实现
     */
    protected abstract void initData();


    //--------------------------------------子类可以选择覆盖的方法----------------------------------


    /**
     * 是否注册eventBus,默认不注册
     *
     * @return false, 不注册
     */
    protected boolean isRegisterEventBus() {
        return false;
    }


    /**
     * 设置是否强制竖屏,默认强制
     * 子类可以重写此方法设置是否强制视屏
     *
     * @return true 强制
     */
    public boolean orientationPortraitEnable() {
        return true;
    }


    /**
     * 设置是否可以滑动,默认不滑动
     *
     * @return true 可以滑动
     */
    public boolean swipeEnable() {
        return false;
    }


    /**
     * 设置是否显示toolbar,默认不显示,显示时,可以使用默认的控件,或添加直接想要的控件
     *
     * @return false, 不显示
     */
    public boolean isShowToolbar() {
        return false;
    }

    /**
     * 是否显示toolbar和内容之间的分割线,默认不显示
     *
     * @return false, 不显示
     */
    public boolean isShowLineHorizontal() {
        return false;
    }


    /**
     * 设置Fragment的转场动画,子类可重写此方法改变转场动画
     *
     * @return 具体动画
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }

    /**
     * 获取Activity 注射器
     *
     * @return  ActivityComponent
     */
    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .activityModule(new ActivityModule())
                .build();
    }


    //-------------------------------------子类可以调用的方法-----------------------------------------------

    /**
     * 设置状态栏背景颜色，不能改变状态栏内容的颜色
     */
    protected void setStateBarBackground(String color) {
        if (!TextUtils.isEmpty(color)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setTintColor(Color.parseColor(color));

        }
    }


    /**
     * 设置点击事件,子类直接使用
     *
     * @param ids 被点击View的ID
     * @return {@link BaseActivity}
     */
    protected BaseActivity setOnClick(@IdRes int... ids) {
        View view;
        for (int id : ids) {
            view = findViewById(id);
            if (null != view) {
                view.setOnClickListener(this);
            }
        }
        return this;
    }


    /**
     * 设置点击事件,子类直接使用
     *
     * @param views 被点击View
     * @return {@link BaseActivity}
     */
    protected BaseActivity setOnClick(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
        return this;
    }


    /**
     * Toast 提示用户
     *
     * @param msg 提示内容String
     */
    @Override
    public void showTipMsg(String msg) {
        ToastUtils.show(msg);
    }

    /**
     * Toast 提示用户
     *
     * @param msg msg 提示内容res目录下面的String的int值
     */
    @Override
    public void showTipMsg(int msg) {
        ToastUtils.show(msg);
    }

    /**
     * 网络请求的时候显示正在加载的对话框
     */
    private DialogInterface.OnKeyListener onKeyListener =
            (dialog, keyCode, event) -> keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;
    @Override
    public void showLoading() {
        if (null == loadingDialog) {
            loadingDialog = new AlertDialog.Builder(this).setView(new ProgressBar(this))
                    .setCancelable(false)
                    .setOnKeyListener(onKeyListener)
                    .create();
            loadingDialog.setCanceledOnTouchOutside(false);
            Window window = loadingDialog.getWindow();
            if (null != window) {
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }



    /**
     * 网络请求完成时隐藏加载对话框
     */
    @Override
    public void hideLoading() {
        if (null != loadingDialog) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
            loadingDialog = null;
        }
    }

    /**
     *
     */
    @Override
    public void invalidToken() {
        //用于检测你当前用户的token是否有效，无效就返回登录界面，具体的业务逻辑你自己实现
        //如果需要做到实时检测，推荐用socket长连接，每隔10秒发送一个验证当前登录用户token是否过期的请求
    }

    /**
     * Finish当前页面，最好实现onBackPressedSupport()，这个方法会有一个退栈操作，
     * 开源框架实现的，我们不用管
     */
    @Override
    public void myFinish() {
        onBackPressedSupport();
    }


    //-------------------------------------默认已使用的方法-----------------------------------





    /**
     * 是否注册eventBus
     *
     * @param registerEventBus boolean,是否注册
     */
    private void setEventBusEnable(boolean registerEventBus) {
        if (registerEventBus&&!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }


    /**
     * 初始化布局
     *
     * @param savedInstanceState 状态保存
     */
    private void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_base);

        //添加自己的布局
        int layoutId = getLayoutId(savedInstanceState);
        if (0 != layoutId) {
            LinearLayout rootLinearLayout = findViewById(R.id.ll_layout_base_activity);
            getLayoutInflater().inflate(layoutId, rootLinearLayout, true);
        }

    }


    /**
     * 初始化toolbar和toolbar与用户布局之间的分割线
     */
    private void initToolbarAndSplitLine() {
        toolbar = findViewById(R.id.toolbar_base_activity);
        //这里不设置的话,后面再设置不会显示后面的内容
        toolbar.setTitle("");
        View lineHorizontal = findViewById(R.id.view_base_activity);

        if (isShowToolbar()) {
            setSupportActionBar(toolbar);
            //添加默认的返回图标
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用(14以后可以不用设置)
        } else {
            toolbar.setVisibility(View.GONE);
        }

        if (!isShowLineHorizontal()) {
            lineHorizontal.setVisibility(View.GONE);
        }
    }


    /**
     * 设置是否强制竖屏,默认强制
     *
     * @param portrait true,强制竖屏
     */
    private void setRequestedOrientation(boolean portrait) {
        if (portrait) {
            //强制竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

}
