package com.example.wanandroid.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;

import androidx.core.content.FileProvider;

import com.example.wanandroid.R;
import com.example.wanandroid.base.MyApplication;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.utils
 * @ClassName: SystemUtil
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/9/6 14:47
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/6 14:47
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class SystemUtil {

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return  手机IMEI
     */
    public static String getIMEI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            if (!TextUtils.isEmpty(tm.getDeviceId())) {
                return tm.getDeviceId();
            }

        }
        return "UnKnown";
    }


    /**
     * @param slotId  slotId为卡槽Id，它的值为 0、1；
     * @return
     */
    public static String getIMEIWithId(Context context, int slotId) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Method method = manager.getClass().getMethod("getImei", int.class);
            String imei = (String) method.invoke(manager, slotId);
            if (!TextUtils.isEmpty(imei)) {
                return imei;
            }
            return "UnKnown";
        } catch (Exception e) {
            return "UnKnown";
        }
    }




    /**
     * 检查WIFI是否连接
     */
    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null;
    }
    /**
     * 检查手机网络(4G/3G/2G)是否连接
     */
    public static boolean isMobileNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetworkInfo != null;
    }
    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)
                MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == cm) {
            return false;
        }

        NetworkInfo info = cm.getActiveNetworkInfo();
        if (null != info && info.isConnected()) {
            return info.getState() == NetworkInfo.State.CONNECTED;
        }
        return false;
    }


    /**
     * 复制字符串
     *
     * @param context   上下文
     * @param text  字符串
     */
    public static void copyToClipBoard(Context context, String text) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        Objects.requireNonNull(mClipboardManager).setPrimaryClip(ClipData.newPlainText(null, text));
        UIUtils.showToast(context.getResources().getString(R.string.copied));
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dp2px(float dpValue) {
        final float scale = MyApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        final float scale = MyApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
