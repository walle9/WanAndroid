package com.example.wanandroid.model.http;


import com.example.wanandroid.utils.SystemUtil;
import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.net.ssl.SSLException;

import retrofit2.HttpException;


/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http
 * @ClassName: FactoryException
 * @Description: 错误工厂
 * @Author: walle
 * @CreateDate: 2019/8/26 20:46
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/26 20:46
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
class FactoryException {


    /**
     * 封装错误
     *
     * @param e 错误
     * @return ApiException    自定义错误
     */
    static ApiException analysisExcetpion(Throwable e) {
        ApiException apiException = new ApiException(e);
        if (!SystemUtil.isNetworkConnected()) {

            apiException.setCode(ErrorInfo.NET_ERROR);
            apiException.setMessage(ErrorInfo.NET_ERROR_MSG);

        } else if (e instanceof HttpException) {

            int code = ((HttpException) e).code();
            apiException.setCode(code);
            apiException.setMessage(ErrorInfo.HTTP_ERROR_MSG);

        } else if (e instanceof UnknownHostException) {

            apiException.setCode(ErrorInfo.UNKOWNHOST_ERROR);
            apiException.setMessage(ErrorInfo.UNKOWNHOST_ERROR_MSG);

        } else if (e instanceof ConnectException) {

            apiException.setCode(ErrorInfo.CONNECT_ERROR);
            apiException.setMessage(ErrorInfo.CONNECT_ERROR_MSG);

        } else if (e instanceof SocketTimeoutException) {

            apiException.setCode(ErrorInfo.SOCKET_TIMEOUT_ERROR);
            apiException.setMessage(ErrorInfo.SOCKET_TIMEOUT_ERROR_MSG);

        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {

            apiException.setCode(ErrorInfo.JSON_ERROR);
            apiException.setMessage(ErrorInfo.JSON_ERROR_MSG);

        } else if (e instanceof SSLException) {

            apiException.setCode(ErrorInfo.SSL_ERROR);
            apiException.setMessage(ErrorInfo.SSL_ERROR_MSG);

        } else {
            switch (((DefinedException) e).getCode()) {
                case ErrorInfo.EXIT:
                    EventBus.getDefault().post("exit");
                    break;
                default:
                    apiException.setCode(((DefinedException) e).getCode());
                    apiException.setMessage(ErrorInfo.UNKNOWN_ERROR_MSG);

            }
        }
        return apiException;
    }


}
