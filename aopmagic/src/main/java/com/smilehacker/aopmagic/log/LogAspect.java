package com.smilehacker.aopmagic.log;

import android.os.Looper;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;

/**
 * Created by quan.zhou on 2017/7/31.
 */

@Aspect
public class LogAspect {
    private final static String TAG = DebugLog.class.getSimpleName();

    public static boolean enable = true;

    @Pointcut("execution(@com.smilehacker.aopmagic.log.DebugLog * *(..))")
    public void DebugLogMethod() {
    }

    @Around("DebugLogMethod()")
    public Object aroundDebugLogMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!enable) {
            return joinPoint.proceed();
        }

        long startTime = System.currentTimeMillis();

        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        Class<?> cls = signature.getDeclaringType();
        String tag = asTag(cls);
        String methodName = signature.getName();
        String[] paramsName = signature.getParameterNames();
        Object[] paramsValue = joinPoint.getArgs();
        StringBuilder builder = new StringBuilder();
        builder.append("⇢ ");
        builder.append(methodName).append("(");
        for (int i = 0; i < paramsName.length; i++) {
            builder.append(paramsName[i])
                    .append("=")
                    .append(paramsValue[i]);
            if (i < paramsName.length - 1) {
                builder.append(", ");
            }
        }
        builder.append(")");

        if (Looper.myLooper() != Looper.getMainLooper()) {
            builder.append(" [Thread:\"").append(Thread.currentThread().getName()).append("\"]");
        }


        Object result = joinPoint.proceed();


        builder.append("\n").append("⇠ ");
        builder.append(methodName);
        builder.append("[").append(System.currentTimeMillis() - startTime).append("ms]");
        builder.append(" = ").append(result);

        Log.d(tag, builder.toString());
        return result;
    }

    private static String asTag(Class<?> cls) {
        if (cls.isAnonymousClass()) {
            return asTag(cls.getEnclosingClass());
        }
        return cls.getSimpleName();
    }

}
