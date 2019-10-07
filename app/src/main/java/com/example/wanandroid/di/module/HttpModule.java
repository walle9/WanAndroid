package com.example.wanandroid.di.module;

import com.example.wanandroid.BuildConfig;
import com.example.wanandroid.base.MyApplication;
import com.example.wanandroid.di.qualifier.ApiUrl;
import com.example.wanandroid.model.http.api.ProtocolHttp;
import com.example.wanandroid.model.http.api.HttpApi;
import com.example.wanandroid.utils.SystemUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.di.module
 * @ClassName: HttpModule
 * @Description: http被注入对象, 网络请求的参数初始化
 * @Author: walle
 * @CreateDate: 2019/8/19 15:20
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 15:20
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

@Module
public class HttpModule {


    ///////////////////////////////////////////////////////////////////////////
    // OKHTTP

    @Provides
    @Singleton
    public OkHttpClient.Builder providesOkHttpHelper() {
        //设置超时时间
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
    }


    @Provides
    @Singleton
    public OkHttpClient provideClient(OkHttpClient.Builder builder) {

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message ->
                    Logger.wtf("==okhttp== %s", message)).setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }

        Cache cache = new Cache(new File(MyApplication.getInstance().getExternalCacheDir(), "okhttpcache"), 10 * 1024 * 1024);

        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            if (!SystemUtil.isNetworkConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (SystemUtil.isNetworkConnected()) {
                int maxAge = 0;
                // 有网络时, 不缓存, 最大保存时长为0
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        };

        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);

        return builder.build();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Retrofit


    /**
     * 创建Retrofit
     *
     * @param builder      Retrofit.Builder
     * @param okHttpClient OkHttpClient
     * @param host         host
     * @return Retrofit
     */
    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient okHttpClient, String host) {
        return builder
                .client(okHttpClient)
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public Retrofit.Builder providesRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    @ApiUrl
    public Retrofit providesApiRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ProtocolHttp.HTTP_HOST);
    }


    ///////////////////////////////////////////////////////////////////////////
    // API

    /**
     * 提供api接口对象
     *
     * @param retrofit retrofit
     * @return api
     */
    @Singleton
    @Provides
    public HttpApi provideApi(@ApiUrl Retrofit retrofit) {
        return retrofit.create(HttpApi.class);
    }
}
