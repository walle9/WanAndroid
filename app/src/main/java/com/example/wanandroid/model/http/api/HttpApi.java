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
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http.api
 * @ClassName: HttpApi
 * @Description: API
 * @Author: walle
 * @CreateDate: 2019/8/19 19:08
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 19:08
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface HttpApi {

    /**
     * 登录时获取验证码
     * @param phone 手机号
     * @return code码
     */
    @FormUrlEncoded
    @POST("")
    Observable<HttpNoResult> loginCode(@Field("phone") String phone);

    /**
     * 获取天气信息
     * @param city
     * @return
     */
    @GET("")
    Observable<HttpResult<WeatherBean>> weather(@Part("city") String city);


    /**
     * 登录时获取验证码
     * @param phone 手机号
     * @return code码
     */
    @FormUrlEncoded
    @POST("")
    Flowable<HttpNoResult> loginaaa(@Field("phone") String phone, @Field("code") String code);


    /**
     * 获取美女图片
     * @return
     */
    @GET(ProtocolHttp.GETGIRLLIST)
    Observable<HttpResult<List<LookerBean>>> getGirlList(@Path("num") int num, @Path("page") int page);

    //----------------------------------------------------------------------

    /**
     * 首页文章列表
     *
     * @param page  页码，拼接在连接中，从0开始
     * @return  HomeListBean
     */
    @GET(ProtocolHttp.HOMEARTICLELIST)
    Observable<HttpResult<HomeListBean>> homeArticleList(@Path("page") int page);

    /**
     * 首页Top库文章
     * @return  HomeListBean
     */
    @GET(ProtocolHttp.HOMEARTICLETOPLIST)
    Observable<HttpResult<List<HomeListBean.DatasBean>>> homeArticleTopList();

    /**
     * 首页banber
     *
     * @return  HomeBannerBean
     */
    @GET(ProtocolHttp.HOMEBANNER)
    Observable<HttpResult<List<HomeBannerBean>>> homeBanner();

    /**
     * 登录
     * @return  LoginBean
     */
    @FormUrlEncoded
    @POST(ProtocolHttp.LOGIN)
    Observable<HttpResult<LoginBean>> login(@Field("username") String username, @Field("password") String password);


    /**
     * 收藏站内文章
     * @param id    文章id
     * @return  HttpNoResult
     */
    @POST(ProtocolHttp.COLLECT)
    Observable<HttpNoResult> collect(@Path("id") int id);

    /**
     * 取消收藏
     * @param id    文章id
     * @return  HttpNoResult
     */
    @POST(ProtocolHttp.UNCOLLECT)
    Observable<HttpNoResult> uncollect(@Path("id") int id);
}
