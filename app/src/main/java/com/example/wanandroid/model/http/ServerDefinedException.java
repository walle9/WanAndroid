package com.example.wanandroid.model.http;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http
 * @ClassName: ServerDefinedException
 * @Description: 服务器自定义异常
 * @Author: walle
 * @CreateDate: 2019/8/28 18:07
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/28 18:07
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ServerDefinedException extends RuntimeException{
    private int code;
    private String msg;

    public ServerDefinedException(int code, String meg) {
        this.code = code;
        this.msg = meg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
