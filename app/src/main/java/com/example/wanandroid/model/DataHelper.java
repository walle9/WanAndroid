package com.example.wanandroid.model;

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
    public Flowable<HttpNoResult> login(String phone, String code) {
        return http.login(phone,code);
    }

    @Override
    public Observable<HttpResult<List<LookerBean>>> getGirlList(int count, int page) {
        return http.getGirlList(count,page);
    }


}
