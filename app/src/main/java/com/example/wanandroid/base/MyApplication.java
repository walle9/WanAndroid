package com.example.wanandroid.base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.example.wanandroid.BuildConfig;
import com.example.wanandroid.di.component.AppComponent;
import com.example.wanandroid.di.component.DaggerAppComponent;
import com.example.wanandroid.di.module.AppModule;
import com.example.wanandroid.di.module.HttpModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid
 * @ClassName: MyApplication
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/8/15 22:36
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/15 22:36
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyApplication extends Application {

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    private static MyApplication instance;
    /**
     * App注射器
     */
    private static AppComponent appComponent;


    public static MyApplication getInstance() {
        return instance;
    }

    private void setInstance(MyApplication instance) {
        MyApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        init();
        //初始化屏幕宽高
        getScreenSize();
    }

    private void init() {
        initLogger();
        initAppComponent();
    }


    public void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }



    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getInstance()))
                .httpModule(new HttpModule())
                .build();
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                // (Optional) Whether to show thread info or not. Default true
                .showThreadInfo(true)
                // (Optional) How many method line to show. Default 2
                .methodCount(2)
                // (Optional) Hides internal method calls up to offset. Default 5
                .methodOffset(5)
                //.logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                // (Optional) Global tag for every log. Default PRETTY_LOGGER
                //.tag("My custom tag")
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){

            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }


    /**
     * 获取App注射器
     *
     * @return AppComponent
     */
    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
