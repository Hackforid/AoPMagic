package com.smilehacker.aopmagic.cache;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by zhouquan on 17/7/30.
 */

@Aspect
public class CacheAspect {

    private final static String TAG = CacheAspect.class.getSimpleName();
    private final ExLruCache mCache = new ExLruCache(100);


    @Pointcut("execution(@com.smilehacker.aopmagic.CacheResult * *(..))")
    public void CacheResultMethod() {
    }

    @Around("CacheResultMethod()")
    public Object aroundCacheResultMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "do cache result >>>");
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(joinPoint.getSignature());
        if (joinPoint.getArgs() != null) {
            for (Object arg : joinPoint.getArgs()) {
                keyBuilder.append(arg.toString());
            }
        }
        String key = keyBuilder.toString();
        Log.d(TAG, "    cache key:" + key);
        Object value = mCache.get(key);
        if (value == null) {
            Log.d(TAG, "    no cache find, process method to get result");
            value = joinPoint.proceed();
            if (value != null) {
                mCache.put(key, value);
            }
        }
        Log.d(TAG, "    result:" + value);

        return value;
    }
}
