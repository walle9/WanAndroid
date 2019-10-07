package com.example.wanandroid.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.bean
 * @ClassName: LookerBean
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/9/4 17:34
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/4 17:34
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LookerBean {


    /**
     * _id : 5ccdbc219d212239df927a93
     * createdAt : 2019-05-04T16:21:53.523Z
     * desc : 2019-05-05
     * publishedAt : 2019-05-04T16:21:59.733Z
     * source : web
     * type : 福利
     * url : http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg
     * used : true
     * who : lijinshanmx
     */

    @SerializedName("_id")
    public String mId;
    @SerializedName("createdAt")
    public String mCreatedAt;
    @SerializedName("desc")
    public String mDesc;
    @SerializedName("publishedAt")
    public String mPublishedAt;
    @SerializedName("source")
    public String mSource;
    @SerializedName("type")
    public String mType;
    @SerializedName("url")
    public String mUrl;
    @SerializedName("used")
    public boolean mUsed;
    @SerializedName("who")
    public String mWho;
    public int height = 0;
}
