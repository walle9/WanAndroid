package com.example.wanandroid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseMvpActivity;
import com.example.wanandroid.contract.WeatherActivityContract;
import com.example.wanandroid.presenter.WeatherActivityPresenter;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.view.activity
 * @ClassName: WeatherActivity
 * @Description: 天气情况
 * @Author: walle
 * @CreateDate: 2019/9/2 20:44
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/2 20:44
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class WeatherActivity extends BaseMvpActivity<WeatherActivityPresenter> implements WeatherActivityContract.IView {

    private TextView mTvWeatherContent;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        basePresenter.loadData(getResources().getString(R.string.city_bengbu));
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_weather;
    }

    @Override
    public void showSuccessful(String msg) {
        mTvWeatherContent.setText(msg);
    }

    @Override
    protected void initView() {
        super.initView();
        mTvWeatherContent = findViewById(R.id.tv_weather_content);
        Button btnNew = findViewById(R.id.btn_new);
        setOnClick(btnNew);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_new:
                startActivity(new Intent(this, WeatherActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void ShowError() {

    }

    @Override
    public boolean swipeEnable() {
        return true;
    }
}
