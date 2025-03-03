package com.media.toolkit.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.media.toolkit.IMediaToolkitFileManager;
import com.media.toolkit.filemanager.FileManager;
import com.media.toolkit.IMediaToolkitService;
import com.media.toolkit.utils.LogUtils;
import com.media.toolkit.utils.SyncTaskUtil;


public class CodecServiceImp extends Service {
    private static final String TAG = "CodecServiceImp";
    private final static int SERVICE_TIMER_OUT = 3000000;
    private FileManager mFileManager;
    private MediaToolkitBinderImp mMediaToolkitBinderImp;
    private Handler mWorkHandler;
    private HandlerThread mWorkHandlerThread;
    private SyncTaskUtil mSyncTaskUtil;

    private void encode(MediaCodec encodeCodec, MediaExtractor mediaExtractor){

    }

    private void decode(MediaCodec decodeCodec, MediaExtractor mediaExtractor){

    }

    public void onCreate(){
        super.onCreate();
        init();
    }

    public void onDestroy(){
        super.onDestroy();
        unInit();
    }

    private void init(){
        mMediaToolkitBinderImp = new MediaToolkitBinderImp();
        mWorkHandlerThread = new HandlerThread(TAG);
        mWorkHandler = new Handler(mWorkHandlerThread.getLooper());
        mWorkHandler.post(()->{
            initPartners();
        });
    }

    private void initPartners(){
        mFileManager = new FileManager();
    }

    private void unInit(){
        mMediaToolkitBinderImp = null;
        mSyncTaskUtil.waitSyncTask(mWorkHandler,()->{
            unInitPartners();
        },SERVICE_TIMER_OUT);
    }

    private void unInitPartners(){
        mFileManager.unInit();
    }

    public class MediaToolkitBinderImp extends IMediaToolkitService.Stub{
        @Override
        public IMediaToolkitFileManager getIMediaToolkitFileManager() throws RemoteException {
            return mFileManager;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.d(TAG,"onBind");
        return mMediaToolkitBinderImp;
    }

}
