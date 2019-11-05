package com.example.wanandroid.model.http.api;

import com.example.wanandroid.model.bean.HomeBannerBean;
import com.example.wanandroid.model.bean.HomeListBean;
import com.example.wanandroid.model.bean.LoginBean;
import com.example.wanandroid.model.bean.LookerBean;
import com.example.wanandroid.model.bean.WeatherBean;
import com.example.wanandroid.model.http.HttpNoResult;
import com.example.wanandroid.model.http.HttpResult;
import com.example.wanandroid.model.http.api.HttpApi;
import com.example.wanandroid.model.http.api.HttpHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http
 * @ClassName: RetrofitHelper
 * @Description: 网络接口Retrofit实现,使用装饰者设计模式,被装饰类
 * @Author: walle
 * @CreateDate: 2019/8/19 19:05
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 19:05
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class RetrofitHelper implements HttpHelper {
    /**
     * 被装饰
     */
    private HttpApi httpApi;

    /**
     * 提供RetrofitHelper
     * 在RetrofitHelper被注入的时候,发现需要HttpApi,HttpApi由HttpModule提供,所以在AppComponent中也需要添加HttpModule
     * @param httpApi api
     */
    @Inject
    public RetrofitHelper(HttpApi httpApi) {
        this.httpApi = httpApi;
    }


    @Override
    public Observable<HttpNoResult> loginCode(String phone) {
        return httpApi.loginCode(phone);
    }

    @Override
    public Observable<HttpResult<WeatherBean>> weather(String city) {
        return httpApi.weather(city);
    }

    @Override
    public Flowable<HttpNoResult> loginaaa(String phone, String code) {
        return httpApi.loginaaa(phone,code);
    }

    @Override
    public Observable<HttpResult<List<LookerBean>>> getGirlList(int count, int page) {
        return httpApi.getGirlList(count,page);
    }

    @Override
    public Observable<HttpResult<HomeListBean>> homeArticleList(int page) {
        return httpApi.homeArticleList(page);
    }

    @Override
    public Observable<HttpResult<List<HomeListBean.DatasBean>>> homeArticleTopList() {
        return httpApi.homeArticleTopList();
    }

    @Override
    public Observable<HttpResult<List<HomeBannerBean>>> homeBanner() {
        return httpApi.homeBanner();
    }

    @Override
    public Observable<HttpResult<LoginBean>> login(String username, String password) {
        return httpApi.login(username,password);
    }

    @Override
    public Observable<HttpNoResult> collect(int id) {
        return httpApi.collect(id);
    }

    @Override
    public Observable<HttpNoResult> uncollect(int id) {
        return httpApi.uncollect(id);
    }


}
