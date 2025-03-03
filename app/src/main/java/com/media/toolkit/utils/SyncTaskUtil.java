package com.media.toolkit.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.os.Handler;

public class SyncTaskUtil {
    public SyncTaskUtil(){}

    /**

     * Create Sync FutureTask.

     */
    public <T> RunnableFuture<T> newTask(Callable<T> callFunc){
        return new FutureTask<T>(callFunc);
    }

    public Future postSyncCallable(Handler handler,Callable callFunc){
        if(handler == null || callFunc == null){
            return null;
        }
        RunnableFuture future = newTask(callFunc);
        boolean posted = handler.post(future);
        return posted ? future : null;
    }

    public <T> T waitSyncTask(Handler handler,Callable<T> callable ,long timeOutMilis){
        if(handler == null || callable == null || timeOutMilis < 0){
            return null;
        }
        Future<T> future = postSyncCallable(handler,callable);
        if(future == null){
            return null;
        }
        T result = null;

        try {
            result = future.get(timeOutMilis, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    public <T> RunnableFuture<T> newTask(Runnable run, T value){
        return new FutureTask<T>(run,value);
    }

    public <T> RunnableFuture<T> postSyncCallable(Handler handler,Runnable run){
        if(handler == null || run == null){
            return null;
        }
        RunnableFuture future = newTask(run,null);
        boolean posted = handler.post(future);
        return posted ? future : null;
    }

    public <T> T waitSyncTask(Handler handler,Runnable run,long timeOutMilis){
        if(handler == null || run == null || timeOutMilis < 0){
            return null;
        }
        Future<T> future = postSyncCallable(handler,run);
        T result  = null;
        try {
            result = future.get(timeOutMilis,TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
