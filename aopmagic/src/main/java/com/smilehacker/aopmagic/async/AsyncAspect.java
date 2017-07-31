package com.smilehacker.aopmagic.async;

import android.os.AsyncTask;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.Executor;

/**
 * Created by quan.zhou on 2017/7/31.
 */

@Aspect
public class AsyncAspect {
    private final static String TAG = AsyncAspect.class.getCanonicalName();
    private static Executor mExecutor;

    @Pointcut("execution(@com.smilehacker.aopmagic.async.Async * *(..))")
    public void AsyncMethod() {
    }

    @Around("AsyncMethod()")
    public void arountAsyncMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "do async>>>");
        AoPAsyncTask task = new AoPAsyncTask();
        if (mExecutor != null) {
            task.executeOnExecutor(mExecutor, joinPoint);
        } else {
            task.execute(joinPoint);
        }
    }

    private static class AoPAsyncTask extends AsyncTask<ProceedingJoinPoint, Void, Void> {
        @Override
        protected Void doInBackground(ProceedingJoinPoint... proceedingJoinPoints) {
            try {
                Log.d(TAG, "    background job start");
                proceedingJoinPoints[0].proceed();
                Log.d(TAG, "    background job end");
            } catch (Throwable throwable) {
                Log.d(TAG, "    got exception in async task", throwable);
            }
            return null;
        }
    }

    public static void setExecutor(Executor executor) {
        mExecutor = executor;
    }
}
