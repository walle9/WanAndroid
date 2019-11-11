package com.example.wanandroid.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.model.bean
 * @ClassName: HotKeyBean
 * @Description: 搜索热词
 * @Author: walle
 * @CreateDate: 2019/11/6 15:27
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/11/6 15:27
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HotKeyBean {


    /**
     * id : 6
     * link :
     * name : 面试
     * order : 1
     * visible : 1
     */

    @SerializedName("id")
    public int mId;
    @SerializedName("link")
    public String mLink;
    @SerializedName("name")
    public String mName;
    @SerializedName("order")
    public int mOrder;
    @SerializedName("visible")
    public int mVisible;
}
