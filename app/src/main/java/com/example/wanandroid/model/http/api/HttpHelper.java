package com.example.wanandroid.model.http.api;

import com.example.wanandroid.model.bean.LookerBean;
import com.example.wanandroid.model.bean.WeatherBean;
import com.example.wanandroid.model.http.HttpNoResult;
import com.example.wanandroid.model.http.HttpResult;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http
 * @ClassName: HttpHelper
 * @Description: 网络接口，接口参数Token统一处理，方法中不传Token,使用装饰者设计模式
 * @Author: walle
 * @CreateDate: 2019/8/19 17:50
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 17:50
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface HttpHelper {

    /**
     * 登录时获取验证码.
     *
     * @param phone 手机号
     * @return {"code":0}
     */
    Observable<HttpNoResult> loginCode(String phone);

    /**
     * 获取某个城市的天气信息
     * @param city 城市
     * @return 天气信息
     */
    Observable<HttpResult<WeatherBean>> weather(String city);
    Flowable<HttpNoResult> login(String phone, String code);
    //Flowable<HttpResult<List<DiyBean>>> diyKeys(String allId);
    Observable<HttpResult<List<LookerBean>>> getGirlList(int count,int page);
}
