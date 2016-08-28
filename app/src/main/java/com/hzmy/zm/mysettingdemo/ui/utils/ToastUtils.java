package com.hzmy.zm.mysettingdemo.ui.utils;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author       追梦
 * @email        1521541979@qq.com
 * @description  Toast统一管理类
 */
public class ToastUtils  
{
    /*是否需要显示，可以在application的onCreat方法中设置*/
    private ToastUtils()
    {  
        /* cannot be instantiated */  
        throw new UnsupportedOperationException("cannot be instantiated");  
    }  
  



    public static void shortToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
    }

    public static void shortToast(Context context, String text) {
        if (!TextUtils.isEmpty(text) && !"".equals(text.trim())) {
            showToast(context, text, Toast.LENGTH_SHORT);
        }
    }

    public static void longToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_LONG);
    }

    public static void longToast(Context context, String text) {
        if (!TextUtils.isEmpty(text) && !"".equals(text.trim())) {
            showToast(context, text, Toast.LENGTH_LONG);
        }
    }

    public static void showToast(Context context, int resId, int duration) {
        if (context == null) {
            return;
        }
        if (context != null && context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        String text = context.getString(resId);
        showToast(context, text, duration);
    }

    public static void showToast(Context context, String text, int duration) {
        if (context == null) {
            return;
        }
        if (context != null && context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (!TextUtils.isEmpty(text) && !"".equals(text.trim())) {
            Toast.makeText(context, text, duration).show();
        }
    }

  
}  