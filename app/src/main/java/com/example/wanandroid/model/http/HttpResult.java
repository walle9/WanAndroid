package com.example.wanandroid.model.http;

import com.google.gson.annotations.SerializedName;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http
 * @ClassName: HttpResult
 * @Description: 有解析数据的返回
 * @Author: walle
 * @CreateDate: 2019/8/19 17:18
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 17:18
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HttpResult<T> {
    private String errorCode;
    private String errorMsg;

    private boolean error;
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }


    @SerializedName(value = "data",alternate = {"result","results"})
    public T data;

    public String getCode() {
        return errorCode;
    }

    public HttpResult setCode(String code) {
        errorCode = code;
        return this;
    }

    public String getMsg() {
        return errorMsg;
    }

    public HttpResult setMsg(String msg) {
        errorMsg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public HttpResult setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + errorCode +
                ", msg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
