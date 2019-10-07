package com.example.wanandroid.model.http;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http
 * @ClassName: HttpNoResult
 * @Description: 没有解析数据的返回
 * @Author: walle
 * @CreateDate: 2019/8/19 17:20
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 17:20
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HttpNoResult {

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public HttpNoResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public HttpNoResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "HttpNoResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
