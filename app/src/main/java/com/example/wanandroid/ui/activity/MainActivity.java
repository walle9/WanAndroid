package com.example.wanandroid.ui.activity;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.ui.fragment.FirstTabFragment;
import com.example.wanandroid.ui.fragment.ForthTabFragment;
import com.example.wanandroid.ui.fragment.SecondTabFragment;
import com.example.wanandroid.ui.fragment.ThirdTabFragment;
import com.example.wanandroid.utils.UIUtils;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.base
 * @ClassName: MainActivity
 * @Description: 主Activity
 * @Author: walle
 * @CreateDate: 2019/8/16 11:27
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/16 11:27
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainActivity extends BaseActivity {

    private LinearLayout mLlMainBottombar;
    private SupportFragment[] mFragments = new SupportFragment[5];
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final int FORTH = 3;
    private static final int FIFTH = 4;

    private static final long WAIT_TIME = 2000L;
    private long touchTime = 0;


    @SuppressLint("CheckResult")
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mLlMainBottombar = findViewById(R.id.ll_main_bottombar);
        initFragmnet();
        initBottombar();
        changeBottombar(0);

    }


    /**
     * 初始化Fragment
     */
    private void initFragmnet() {
        SupportFragment firstFragment = findFragment(FirstTabFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = FirstTabFragment.newInstance();
            mFragments[SECOND] = SecondTabFragment.newInstance();
            mFragments[THIRD] = ThirdTabFragment.newInstance();
            mFragments[FORTH] = ForthTabFragment.newInstance();
            mFragments[FIFTH] = ForthTabFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FORTH],
                    mFragments[FIFTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(SecondTabFragment.class);
            mFragments[THIRD] = findFragment(ThirdTabFragment.class);
            mFragments[FORTH] = findFragment(ForthTabFragment.class);
        }
    }

    /**
     * 初始化底部导航
     */
    private void initBottombar() {
        String[] stringBottombar = getResources().getStringArray(R.array.bottombar_title);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.bottombar_img);

        for (int i = 0; i < stringBottombar.length; i++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_bottombar, mLlMainBottombar, false);
            TextView tvItemBottombar = inflate.findViewById(R.id.tv_item_bottombar);
            ImageView ivItemBottombar = inflate.findViewById(R.id.iv_item_bottombar);
            tvItemBottombar.setText(stringBottombar[i]);
            ivItemBottombar.setImageResource(typedArray.getResourceId(i, 0));
            mLlMainBottombar.addView(inflate);
        }

        typedArray.recycle();
        setItemListener();

    }

    /**
     * 设置底部导航监听
     */
    private void setItemListener() {
        int childCount = mLlMainBottombar.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childAt = mLlMainBottombar.getChildAt(i);
            childAt.setOnClickListener(onClickListener);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            changeSelector(mLlMainBottombar.indexOfChild(view));
        }
    };

    private void changeSelector(int index) {
        changeBottombar(index);
        changeFragemnt(index);
    }

    /**
     * 改变Fragment 选中位置
     *
     * @param index 选中位置
     */
    private void changeFragemnt(int index) {
        showHideFragment(mFragments[index]);
    }

    /**
     * 改变底部导航选中位置
     *
     * @param index 选中位置
     */
    private void changeBottombar(int index) {
        int childCount = mLlMainBottombar.getChildCount();
        for (int i = 0; i < childCount; i++) {
            mLlMainBottombar.getChildAt(i).setSelected(i == index);
        }
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressedSupport() {
        //再按一次退出
        if (System.currentTimeMillis() - touchTime < WAIT_TIME) {
            finish();
        } else {
            touchTime = System.currentTimeMillis();
            UIUtils.showToast(R.string.press_again_exit);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
