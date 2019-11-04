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
    private int errorCode;
    private String errorMsg;

    @SerializedName(value = "data",alternate = {"result","results"})
    public T data;

    public int getCode() {
        return errorCode;
    }

    public HttpResult setCode(int code) {
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
