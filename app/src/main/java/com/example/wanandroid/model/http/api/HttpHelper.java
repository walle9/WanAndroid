package com.example.wanandroid.model.http.api;

import com.example.wanandroid.model.bean.HomeBannerBean;
import com.example.wanandroid.model.bean.HomeListBean;
import com.example.wanandroid.model.bean.LoginBean;
import com.example.wanandroid.model.bean.LookerBean;
import com.example.wanandroid.model.bean.WeatherBean;
import com.example.wanandroid.model.http.HttpNoResult;
import com.example.wanandroid.model.http.HttpResult;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.Path;

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
    Flowable<HttpNoResult> loginaaa(String phone, String code);
    //Flowable<HttpResult<List<DiyBean>>> diyKeys(String allId);
    Observable<HttpResult<List<LookerBean>>> getGirlList(int count,int page);

    //-------------------------------

    /**
     * 获取首页文章列表
     * @param page  页码，拼接在连接中，从0开始
     * @return  HomeListBean,首页文章列表
     */
    Observable<HttpResult<HomeListBean>> homeArticleList(int page);

    /**
     * 获取首页top库列表
     * @return  HomeListBean
     */
    Observable<HttpResult<List<HomeListBean.DatasBean>>> homeArticleTopList();

    /**
     * 获取首页banber
     * @return  HomeBannerBean,首页轮播数据
     */
    Observable<HttpResult<List<HomeBannerBean>>> homeBanner();

    /**
     * 登录
     * @param username  用户名
     * @param password  密码
     * @return  LoginBean
     */
    Observable<HttpResult<LoginBean>> login(String username, String password);

    /**
     * 收藏站内文章
     * @param id 文章id
     * @return  HttpNoResult
     */
    Observable<HttpNoResult> collect(int id);

    /**
     * 取消收藏
     * @param id 文章id
     * @return  HttpNoResult
     */
    Observable<HttpNoResult> uncollect(int id);
}
