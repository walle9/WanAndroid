package com.example.wanandroid.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.utils.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import me.jessyan.autosize.internal.CancelAdapt;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.view.activity
 * @ClassName: SplashActivity
 * @Description: 启动页,不能继承滑动的SwipeBackActivity和使用autosize(实现CancelAdapt取消)
 * @Author: walle
 * @CreateDate: 2019/9/5 20:51
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/5 20:51
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class SplashActivity extends SupportActivity implements CancelAdapt {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        requestPermission();
    }

    /**
     * 申请权限
     */
    @SuppressLint("CheckResult")
    private void requestPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {

                        new Handler().postDelayed(() -> {
                            Intent intent = new Intent();
                            intent.setClass(SplashActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        },1200);

                    } else {
                        finish();
                    }
                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        requestPermission();
    }



}
