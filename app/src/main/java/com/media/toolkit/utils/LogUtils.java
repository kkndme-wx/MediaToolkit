package com.media.toolkit.utils;

import android.util.Log;

import com.media.toolkit.BuildConfig;

public class LogUtils {
    private final static String TAG = "MediaToolkit";

    public static void d(String SubTag, String msg) {
        Log.d(TAG, "|" + BuildConfig.VERSION_NAME + "| " + SubTag + " " + msg);
    }

    public static void e(String SubTag, String msg) {
        Log.e(TAG, "|" + BuildConfig.VERSION_NAME + "| " + SubTag + " " + msg);

    }

    public static void w(String SubTag, String msg) {
        Log.w(TAG, "|" + BuildConfig.VERSION_NAME + "| " + SubTag + " " + msg);

    }

    public static void i(String SubTag, String msg) {
        Log.i(TAG, "|" + BuildConfig.VERSION_NAME + "|" + SubTag + " " + msg);
    }
}
