package com.smilehacker.aopmagic.safe;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by quan.zhou on 2017/7/31.
 */

@Aspect
public class SafeAspect {
    private final static String TAG = SafeAspect.class.getSimpleName();

    @Pointcut("execution(@com.smilehacker.aopmagic.safe.Safe * *(..))")
    public void SafeMethod() {
    }

    @Around("SafeMethod()")
    public Object arountSafeMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "do safe >>>");
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            Log.e(TAG, "    catch exception", e);
            return null;
        }
    }
}
