package com.example.wanandroid.model.http.api;

import com.example.wanandroid.model.bean.HomeBannerBean;
import com.example.wanandroid.model.bean.HomeListBean;
import com.example.wanandroid.model.bean.LoginBean;
import com.example.wanandroid.model.bean.LookerBean;
import com.example.wanandroid.model.bean.WeatherBean;
import com.example.wanandroid.model.http.api.HttpHelper;
import com.example.wanandroid.model.http.HttpNoResult;
import com.example.wanandroid.model.http.HttpResult;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model
 * @ClassName: DataHelper
 * @Description: 网络接口的实现类.装饰类,可以对装饰者进行扩展,这里什么都没做
 * @Author: walle
 * @CreateDate: 2019/8/19 18:01
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 18:01
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class DataHelper implements HttpHelper {

    private HttpHelper http;

    public DataHelper(HttpHelper http) {
        this.http = http;
    }

    @Override
    public Observable<HttpNoResult> loginCode(String phone) {
        return http.loginCode(phone);
    }

    @Override
    public Observable<HttpResult<WeatherBean>> weather(String city) {
        return http.weather(city);
    }

    @Override
    public Flowable<HttpNoResult> loginaaa(String phone, String code) {
        return http.loginaaa(phone,code);
    }

    @Override
    public Observable<HttpResult<List<LookerBean>>> getGirlList(int count, int page) {
        return http.getGirlList(count,page);
    }

    @Override
    public Observable<HttpResult<HomeListBean>> homeArticleList(int page) {
        return http.homeArticleList(page);
    }

    @Override
    public Observable<HttpResult<List<HomeListBean.DatasBean>>> homeArticleTopList() {
        return http.homeArticleTopList();
    }

    @Override
    public Observable<HttpResult<List<HomeBannerBean>>> homeBanner() {
        return http.homeBanner();
    }

    @Override
    public Observable<HttpResult<LoginBean>> login(String username, String password) {
        return http.login(username,password);
    }

    @Override
    public Observable<HttpNoResult> collect(int id) {
        return http.collect(id);
    }

    @Override
    public Observable<HttpNoResult> uncollect(int id) {
        return http.uncollect(id);
    }

}
