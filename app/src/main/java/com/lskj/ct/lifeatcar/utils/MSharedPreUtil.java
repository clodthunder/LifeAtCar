package com.lskj.ct.lifeatcar.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.lskj.ct.lifeatcar.app.LifeApp;

/**
 * Created by thunder on 2018/2/4.
 */

public class MSharedPreUtil {
    public final static String SHARE_NAME = "mshared_pre";

    private static volatile MSharedPreUtil instance;

    /**
     * @param key
     * @param value
     */
    public static void putString(String key, String value) {
        SharedPreferences.Editor editor =
                LifeApp.getInstance().getSharedPreferences(SHARE_NAME,
                        Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * get value by key
     *
     * @param key
     */
    public static String getString(String key) {
        return LifeApp.getInstance().getSharedPreferences(SHARE_NAME,
                Context.MODE_PRIVATE).getString(key, null);
    }

    /**
     * @param key
     * @param value
     */
    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = LifeApp.getInstance().getSharedPreferences(SHARE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * get value by key
     *
     * @param key
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return LifeApp.getInstance().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE)
                .getBoolean(key, defValue);
    }

}
