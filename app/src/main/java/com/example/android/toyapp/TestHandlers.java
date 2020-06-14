package com.example.android.toyapp;

import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.NonNull;


import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;


public class TestHandlers {

    public TestHandlers() {
        final HandlerManager handler1 = new HandlerManager("thread 1");
        final HandlerManager handler2 = new HandlerManager("thread 2");


        final HandlerThread osservaroreThread;
        final Handler osservarore;

        osservaroreThread = new HandlerThread(this.getClass() + ".Thread");
        osservaroreThread.start();
        osservarore = new Handler(osservaroreThread.getLooper());
        osservarore.post(()->{
            while(true){
                Log.w(TAG,"work pending 1? %1$s", handler1.hasWorkPending());
                Log.w(TAG,"work pending 2? %1$s", handler2.hasWorkPending());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        });

        handler1.post(()->{
            Log.e(TAG,"A start");
            for (int i = 0; i < 10000; i++){
                if (i % 101 == 0){
                    Log.v(TAG,"A work %1$s", i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {

                    }
                }
            }
            Log.e(TAG,"A end");
        });


        handler2.post(()->{
            Log.e(TAG,"B start");
            for (int i = 0; i < 10000; i++){
                if (i % 101 == 0){
                    Log.v(TAG,"B work %1$s", i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {

                    }
                }
            }
            Log.e(TAG,"B end");
        });



        handler2.post(()->{
            Log.e(TAG,"C start");
            for (int i = 0; i < 10000; i++){
                if (i % 101 == 0){
                    Log.v(TAG,"C work %1$s", i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {

                    }
                }
            }
            Log.e(TAG,"C end");
        });


    }

    private class HandlerManager {
        public HandlerManager(@NonNull final String name) {
            this.mThread = new HandlerThread(String.format("%1$s-%2$s.Thread", this.getClass(), name));
            this.mThread.start();
            this.mHandler = new Handler(mThread.getLooper());
        }

        public void quit(){
            synchronized (mTokens){
                this.mThread.quit();
                this.mTokens.clear();
            }
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            this.quit();
        }

        public boolean hasWorkPending(){
            synchronized (mTokens){
                return !mTokens.isEmpty();
            }
        }


        public void post(@NonNull final Runnable work) {
            Log.e(TAG,"post");
            final Object token = new Object();
            mHandler.post(()->{
                synchronized (mTokens) {
                    mTokens.add(token);
                }
                try{
                    work.run();
                }
                finally {
                    synchronized (mTokens) {
                        final boolean res = mTokens.remove(token);
                        Log.e(TAG, "removed task? %1$s", res);
                    }
                }
            });
            Log.e(TAG,"posted");
        }

        private final Set<Object> mTokens = Collections.synchronizedSet(new LinkedHashSet<>());
        private final Handler mHandler;
        private final HandlerThread mThread;
    }

    private static final String TAG = TestHandlers.class.getSimpleName();
}

