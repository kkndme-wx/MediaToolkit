package com.media.toolkit;

import android.app.Application;
import android.os.Looper;

import com.media.toolkit.service.CodecServiceClient;
import com.media.toolkit.utils.LogUtils;

public class MediaToolkitApp extends Application {
    private static final String TAG = "MediaToolkitApp";
    private MediaToolkitApp SAppContext;

    public MediaToolkitApp(){
        SAppContext = this;
        LogUtils.d(TAG,"Construct");
    }

    public void onCreate(){
        super.onCreate();
        CodecServiceClient.GetInstance().init(this, Looper.getMainLooper());
    }

}
