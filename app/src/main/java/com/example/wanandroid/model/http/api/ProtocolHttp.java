package com.example.wanandroid.model.http.api;

/**
 * @date create at 2019/4/2 18:14
 * @describe 描述:网络地址
 */
public interface ProtocolHttp {
    String HTTP_HOST = "http://gank.io/api/";

    /**
     * Retrofit在传接口的时候，如果传的是一个完整的链接，在拦截器里面
     * 获取的url，就不会拼接前面host，如果传的不是一个完整的链接，则会拼接
     */
    String GETGIRLLIST = "data/福利/{num}/{page}";//获取美女图片
}
