package com.example.wanandroid.di.module;

import com.example.wanandroid.base.MyApplication;
import com.example.wanandroid.manager.AppManager;
import com.example.wanandroid.model.DataHelper;
import com.example.wanandroid.model.http.api.HttpHelper;
import com.example.wanandroid.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.di.module
 * @ClassName: AppModule
 * @Description: App被注入对象
 * @Author: walle
 * @CreateDate: 2019/8/19 15:17
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 15:17
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
@Module
public class AppModule {
    private MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }


    /**
     * 被装饰者
     * @param retrofitHelper    直接使用RetrofitHelper构造注入
     * @return  HttpHelper
     */
    @Provides
    @Singleton
    public HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    /**
     * 装饰者,这里最终提供了DataHelper
     * @param httpHelper    HttpHelper
     * @return  DataHelper
     */
    @Provides
    @Singleton
    public DataHelper provideDataHelper(HttpHelper httpHelper) {
        return new DataHelper(httpHelper);
    }

    /**
     * 提供AppManager
     * @return  AppManager
     */
    @Provides
    @Singleton
    public AppManager provideAppManager(){
        return new AppManager();
    }

}
