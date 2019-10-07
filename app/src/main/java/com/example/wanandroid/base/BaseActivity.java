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
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.wanandroid.R;
import com.example.wanandroid.di.component.ActivityComponent;
import com.example.wanandroid.di.component.DaggerActivityComponent;
import com.example.wanandroid.di.module.ActivityModule;
import com.example.wanandroid.utils.ToastUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
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
    private Toolbar toolbar;
    private TextView tvToolbarTitle;
    private TextView tvToolbarRight;
    private TextView tvBack;
    private View lineHorizontal;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置是否可以滑动返回,默认不可滑动
        setSwipeBackEnable(swipeEnable());
        //设置是否强制竖屏,默认强制
        setRequestedOrientation(orientationPortraitEnable());

        //设置布局
        initLayout(savedInstanceState);

        //初始化默认及用户添加的控件
        initDefaultView();

        //设置点击事件
        setOnClick(R.id.tv_back_base_activity);

        //初始化数据
        initData();

        MyApplication.getAppComponent().getAppManager().addActivity(this);

    }

    @Override
    protected void onDestroy() {
        MyApplication.getAppComponent().getAppManager().finishActivity(this);
        super.onDestroy();
    }

    //--------------------------------------子类必须实现的方法----------------------------------

    /**
     * 加载数据的方法,交由子类实现
     */
    protected abstract void initData();

    /**
     * 初始化布局,交由子类实现
     */
    protected abstract void initView();


    /**
     * 获取当前布局对象,交由子类实现
     *
     * @param savedInstanceState 这个是当前activity保存的数据，最常见的就是横竖屏切换的时候，
     *                           数据丢失问题
     * @return 当前布局的int值
     */
    protected abstract int getLayoutId(Bundle savedInstanceState);


    //--------------------------------------子类可以选择覆盖的方法----------------------------------


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


    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_back_base_activity) {
            onBackPressedSupport();
        }
    }


    /**
     * 设置Fragment的转场动画,子类可重写此方法改变转场动画
     *
     * @return
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }

    /**
     * 获取Activity 注射器
     *
     * @return
     */
    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .activityModule(new ActivityModule())
                .build();
    }


    //-------------------------------------子类可以调用的方法-----------------------------------------------

    /**
     * 初始化toolbar的内容,子类使用该方法修改toolbar
     *
     * @param isShowToolbar        是否显示toolbar
     * @param isShowBack           是否显示左边的TextView
     * @param isShowMore           是否显示右边的TextView
     * @param isShowLineHorizontal 是否显示显示水平线
     * @return 当前activity对象，可以连点
     */
    protected BaseActivity initToolbar(boolean isShowToolbar, boolean isShowBack,
                                       boolean isShowMore, boolean isShowLineHorizontal) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            if (isShowToolbar) {
                actionBar.show();
                tvBack = findViewById(R.id.tv_back_base_activity);
                TextView textView = findViewById(R.id.tv_right_base_activity);
                if (null != tvBack && null != textView) {
                    tvBack.setVisibility(isShowBack ? View.VISIBLE : View.INVISIBLE);
                    textView.setVisibility(isShowMore ? View.VISIBLE : View.INVISIBLE);
                }
            } else {
                actionBar.hide();
                toolbar.setVisibility(View.GONE);

            }
        }
        if (!isShowLineHorizontal) {
            lineHorizontal.setVisibility(View.GONE);
        }



        return this;
    }


    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    @SuppressWarnings("unused")
    public BaseActivity setMyTitle(String title) {
        tvToolbarTitle.setText(title);
        return this;
    }


    /**
     * 设置标题
     *
     * @param stringId
     * @return
     */
    public BaseActivity setMyTitle(@StringRes int stringId) {
        tvToolbarTitle.setText(stringId);
        return this;
    }


    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public BaseActivity setTitles(CharSequence title) {
        tvToolbarTitle.setText(title);
        return this;
    }


    /**
     * 设置Toolbar背景颜色
     *
     * @param colorId
     * @return
     */
    public BaseActivity setToolbarBack(int colorId) {
        toolbar.setBackgroundColor(getResources().getColor(colorId));
        return this;
    }


    /**
     * 设置左边内容.同时清空左边背景
     *
     * @param leftTitle 内容
     * @return {@link BaseActivity}
     */
    public BaseActivity setLeftTitle(String leftTitle) {
        if (tvBack != null) {
            tvBack.setBackground(null);
            tvBack.setText(leftTitle);
        }
        return this;
    }


    /**
     * 设置左边内容.同时清空左边背景
     *
     * @param leftTitle 内容
     */
    public void setLeftTitle(@StringRes int leftTitle) {
        if (tvBack != null) {
            tvBack.setBackground(null);
            tvBack.setText(leftTitle);
        }
    }


    /**
     * 设置左边的背景
     *
     * @param resId
     * @return
     */
    @SuppressWarnings("unused")
    protected BaseActivity setLeftBackground(int resId) {
        tvBack.setBackgroundResource(resId);
        return this;
    }


    /**
     * 设置标题右边更多的内容
     *
     * @param moreTitle
     */
    public void setMoreTitle(String moreTitle) {
        tvToolbarRight.setText(moreTitle);
    }


    /**
     * 设置标题右边更多的内容
     *
     * @param stringId
     * @return
     */
    public BaseActivity setMoreTitle(@StringRes int stringId) {
        tvToolbarRight.setText(stringId);
        return this;
    }


    /**
     * 设置右边更多的背景,默认没有
     *
     * @param resId
     * @return
     */
    @SuppressWarnings("unused")
    protected BaseActivity setMoreBackground(int resId) {
        tvToolbarRight.setBackgroundResource(resId);
        return this;
    }


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
    public BaseActivity setOnClick(@IdRes int... ids) {
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
    public BaseActivity setOnClick(View... views) {
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
        ToastUtils.showTipMsg(msg);
    }

    /**
     * Toast 提示用户
     *
     * @param msg msg 提示内容res目录下面的String的int值
     */
    @Override
    public void showTipMsg(int msg) {
        ToastUtils.showTipMsg(msg);
    }


    //------------------------------------子类可以被调用的方法------------------------------------

    private DialogInterface.OnKeyListener onKeyListener =
            (dialog, keyCode, event) -> keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;

    /**
     * 网络请求的时候显示正在加载的对话框
     */
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
     * 初始化布局
     *
     * @param savedInstanceState 状态保存
     */
    private void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_base);

        //添加自己的布局
        int layoutId = getLayoutId(savedInstanceState);
        if (0!=layoutId) {
            LinearLayout rootLinearLayout = findViewById(R.id.ll_layout_base_activity);
            getLayoutInflater().inflate(layoutId, rootLinearLayout, true);
        }

    }


    /**
     * 初始化控件的方法
     */
    protected void initDefaultView() {
        toolbar = findViewById(R.id.toolbar_base_activity);
        tvToolbarTitle = findViewById(R.id.tv_title_base_activity);
        tvToolbarRight = findViewById(R.id.tv_right_base_activity);
        lineHorizontal = findViewById(R.id.view_base_activity);
        //初始化控件
        initView();
    }


    /**
     * 设置是否强制竖屏,默认强制
     *
     * @param portrait true,强制竖屏
     */
    protected void setRequestedOrientation(boolean portrait) {
        if (portrait) {
            //强制竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

}
