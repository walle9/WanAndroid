package com.example.wanandroid.model.http.api;

import com.example.wanandroid.model.bean.HomeListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @date create at 2019/4/2 18:14
 * @describe 描述:网络地址
 */
public interface ProtocolHttp {
    String HTTP_HOST = "https://www.wanandroid.com/";

    /**
     * Retrofit在传接口的时候，如果传的是一个完整的链接，在拦截器里面
     * 获取的url，就不会拼接前面host，如果传的不是一个完整的链接，则会拼接
     */


    String GETGIRLLIST = "data/福利/{num}/{page}";                //获取美女图片

    String HOMEARTICLELIST = "article/list/{page}/json";        //获取首页文章列表
    String HOMEARTICLETOPLIST = "article/top/json";        //获取首页至置顶列表
    String HOMEBANNER = "banner/json";                          //获取首页banner
    String LOGIN = "user/login";                                //登录
    String COLLECT = "lg/collect/{id}/json";                    //收藏站内文章
    String UNCOLLECT = "lg/uncollect_originId/{id}/json";       //取消收藏

}
