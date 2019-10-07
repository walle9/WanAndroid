package com.example.wanandroid.model.http;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http
 * @ClassName: ApiException
 * @Description: 接口异常判断处理
 * @Author: walle
 * @CreateDate: 2019/8/19 20:35
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 20:35
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ApiException extends Exception{
    private int code;
    private String message;

    public ApiException(Throwable cause) {
        super(cause);
    }


    public ApiException(Throwable cause, int code, String message) {
        super(message,cause);
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
