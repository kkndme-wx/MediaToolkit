package com.media.toolkit.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;

import android.os.Handler;

import com.media.toolkit.IMediaToolkitService;
import com.media.toolkit.utils.LogUtils;

public class CodecServiceClient {
    private static final String TAG = "CodecServiceClient";
    private Context mContext;
    private Handler mClientThreadHandler;
    private IMediaToolkitService mRemoteService;
    private boolean mIsServiceReady = false;

    private static  CodecServiceClient sCodecServiceClient = new CodecServiceClient();

    public static CodecServiceClient GetInstance(){
        return sCodecServiceClient;
    }

    private ServiceConnection mServiceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            LogUtils.d(TAG,"onServiceConnected----------");
            mClientThreadHandler.post(()->{
                mRemoteService = IMediaToolkitService.Stub.asInterface(binder);
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.d(TAG,"onServiceDisconnected----------");
            mClientThreadHandler.post(()->{
                mRemoteService = null;
            });
        }
    };

    public void init(Context context, Looper looper){
        mContext = context;
        mClientThreadHandler = new Handler(looper);
        Intent intent = new Intent(context,CodecServiceImp.class);
        mContext.bindService(intent,mServiceCon,Context.BIND_AUTO_CREATE);
    }


}
