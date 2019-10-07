package com.example.wanandroid.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @date create at 2019/4/3 15:18
 * @describe 描述:
 */
public class WeatherBean {

    /**
     * yesterday : {"date":"2日星期二","high":"高温 20℃","fx":"东南风","low":"低温 9℃","fl":"<![CDATA[<3级]]>","type":"阴"}
     * city : 蚌埠
     * aqi : null
     * forecast : [{"date":"3日星期三","high":"高温 21℃","fengli":"<![CDATA[3-4级]]>","low":"低温 8℃","fengxiang":"东风","type":"多云"},{"date":"4日星期四","high":"高温 21℃","fengli":"<![CDATA[<3级]]>","low":"低温 11℃","fengxiang":"南风","type":"多云"},{"date":"5日星期五","high":"高温 25℃","fengli":"<![CDATA[3-4级]]>","low":"低温 13℃","fengxiang":"南风","type":"晴"},{"date":"6日星期六","high":"高温 29℃","fengli":"<![CDATA[3-4级]]>","low":"低温 14℃","fengxiang":"西南风","type":"晴"},{"date":"7日星期天","high":"高温 30℃","fengli":"<![CDATA[3-4级]]>","low":"低温 10℃","fengxiang":"西风","type":"多云"}]
     * ganmao : 天凉，昼夜温差较大，较易发生感冒，请适当增减衣服，体质较弱的朋友请注意适当防护。
     * wendu : 20
     */

    @SerializedName("yesterday")
    public YesterdayBean mYesterday;
    @SerializedName("city")
    public String mCity;
    @SerializedName("aqi")
    public Object mAqi;
    @SerializedName("ganmao")
    public String mGanmao;
    @SerializedName("wendu")
    public String mWendu;
    @SerializedName("forecast")
    public List<ForecastBean> mForecast;

    @Override
    public String toString() {
        return "WeatherBean{" +
                "mYesterday=" + mYesterday +
                ", mCity='" + mCity + '\'' +
                ", mAqi=" + mAqi +
                ", mGanmao='" + mGanmao + '\'' +
                ", mWendu='" + mWendu + '\'' +
                ", mForecast=" + mForecast +
                '}';
    }

    public static class YesterdayBean {
        /**
         * date : 2日星期二
         * high : 高温 20℃
         * fx : 东南风
         * low : 低温 9℃
         * fl : <![CDATA[<3级]]>
         * type : 阴
         */

        @SerializedName("date")
        public String mDate;
        @SerializedName("high")
        public String mHigh;
        @SerializedName("fx")
        public String mFx;
        @SerializedName("low")
        public String mLow;
        @SerializedName("fl")
        public String mFl;
        @SerializedName("type")
        public String mType;
    }

    public static class ForecastBean {
        /**
         * date : 3日星期三
         * high : 高温 21℃
         * fengli : <![CDATA[3-4级]]>
         * low : 低温 8℃
         * fengxiang : 东风
         * type : 多云
         */

        @SerializedName("date")
        public String mDate;
        @SerializedName("high")
        public String mHigh;
        @SerializedName("fengli")
        public String mFengli;
        @SerializedName("low")
        public String mLow;
        @SerializedName("fengxiang")
        public String mFengxiang;
        @SerializedName("type")
        public String mType;

        @Override
        public String toString() {
            return "ForecastBean{" +
                    "mDate='" + mDate + '\'' +
                    ", mHigh='" + mHigh + '\'' +
                    ", mFengli='" + mFengli + '\'' +
                    ", mLow='" + mLow + '\'' +
                    ", mFengxiang='" + mFengxiang + '\'' +
                    ", mType='" + mType + '\'' +
                    '}';
        }
    }
}
