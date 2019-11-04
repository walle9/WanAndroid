package com.example.wanandroid.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.model.bean
 * @ClassName: HomeBannerBean
 * @Description: 首页轮播
 * @Author: walle
 * @CreateDate: 2019-10-19 11:33:14
 * @UpdateUser: 更新者：
 * @UpdateDate:
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HomeBannerBean {


    /**
     * desc : Android高级进阶直播课免费学习
     * id : 23
     * imagePath : https://wanandroid.com/blogimgs/67c28e8c-2716-4b78-95d3-22cbde65d924.jpeg
     * isVisible : 1
     * order : 0
     * title : Android高级进阶直播课免费学习
     * type : 0
     * url : https://url.163.com/4bj
     */

    @SerializedName("desc")
    public String mDesc;
    @SerializedName("id")
    public int mId;
    @SerializedName("imagePath")
    public String mImagePath;
    @SerializedName("isVisible")
    public int mIsVisible;
    @SerializedName("order")
    public int mOrder;
    @SerializedName("title")
    public String mTitle;
    @SerializedName("type")
    public int mType;
    @SerializedName("url")
    public String mUrl;
}
