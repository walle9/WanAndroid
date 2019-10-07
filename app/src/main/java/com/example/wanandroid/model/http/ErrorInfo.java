package com.example.wanandroid.model.http;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http
 * @ClassName: ErrorInfo
 * @Description: 网络请求状态码
 * @Author: walle
 * @CreateDate: 2019/8/19 20:41
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 20:41
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface ErrorInfo {

    public static final String SUCCESS = "0";

    public static final int HTTP_ERROR = -1;
    public static final String HTTP_ERROR_MSG = "请求错误";

    public static final int UNKOWNHOST_ERROR = -2;
    public static final String UNKOWNHOST_ERROR_MSG = "连接失败";

    public static final int CONNECT_ERROR = -3;
    public static final String CONNECT_ERROR_MSG = "连接错误";

    public static final int SOCKET_TIMEOUT_ERROR = -4;
    public static final String SOCKET_TIMEOUT_ERROR_MSG = "连接超时";

    public static final int JSON_ERROR = -5;
    public static final String JSON_ERROR_MSG = "json解析错误";

    public static final int UNKNOWN_ERROR = -999;
    public static final String UNKNOWN_ERROR_MSG = "未知错误";

    /**
     * 后台约定数据,退出登录
     */
    public static final int EXIT = 999;
    public static final String EXIT_MSG = "退出登录";














}
