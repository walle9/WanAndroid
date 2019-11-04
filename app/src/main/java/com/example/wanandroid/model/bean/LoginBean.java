package com.example.wanandroid.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by walle9 on 2019/10/23.
 * 描述:
 */
public class LoginBean {


    /**
     * admin : false
     * chapterTops : []
     * collectIds : [8996,9820]
     * email :
     * icon :
     * id : 31444
     * nickname : jingyarufeng
     * password :
     * publicName : jingyarufeng
     * token :
     * type : 0
     * username : jingyarufeng
     */

    @SerializedName("admin")
    public boolean admin;
    @SerializedName("email")
    public String email;
    @SerializedName("icon")
    public String icon;
    @SerializedName("id")
    public int id;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("password")
    public String password;
    @SerializedName("publicName")
    public String publicName;
    @SerializedName("token")
    public String token;
    @SerializedName("type")
    public int type;
    @SerializedName("username")
    public String username;
    @SerializedName("chapterTops")
    public List<?> chapterTops;
    @SerializedName("collectIds")
    public List<Integer> collectIds;
}
