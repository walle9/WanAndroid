package com.example.wanandroid.utils;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.example.wanandroid.base.MyApplication;
import com.google.android.material.textfield.TextInputEditText;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.utils
 * @ClassName: UIUtils
 * @Description: 控件工具类
 * @Author: walle
 * @CreateDate: 2019/10/24 16:42
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/10/24 16:42
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class UIUtils {


    // 获取文本内容
    public static String getTextContent(TextView textView){
        return textView.getText().toString().trim();
    }

    public static String getTextContent(EditText textView){
        return textView.getText().toString().trim();
    }

    public static String getTextContent(TextInputEditText textView){

        Editable text = textView.getText();
        if (null!=text) {
            return text.toString().trim();
        }
        return "";
    }


    // 设置控件enable状态
    public static void setViewEnable(View view, boolean enable) {
        if (enable) {
            if (!view.isEnabled()) {
                view.setEnabled(true);
            }
        } else {
            if (view.isEnabled()) {
                view.setEnabled(false);
            }
        }
    }


    // Toast
    private static Toast toast;

    @SuppressWarnings("all")
    private static void init() {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(), "", Toast.LENGTH_SHORT);
        }
    }

    public static void showToast(String msg) {
        if (null == toast) {
            init();
        }
        toast.setText(msg);
        toast.show();
    }

    public static void showToast(@StringRes int msg) {
        if (null == toast) {
            init();
        }
        toast.setText(msg);
        toast.show();
    }


    // 隐藏软键盘
    public static void hideKeyboard(Context context,View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
