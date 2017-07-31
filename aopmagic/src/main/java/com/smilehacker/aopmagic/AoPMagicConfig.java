package com.smilehacker.aopmagic;

import com.smilehacker.aopmagic.async.AsyncAspect;
import com.smilehacker.aopmagic.cache.CacheAspect;
import com.smilehacker.aopmagic.cache.ExLruCache;
import com.smilehacker.aopmagic.log.DebugLog;
import com.smilehacker.aopmagic.log.LogAspect;

import java.util.concurrent.Executor;

/**
 * Created by quan.zhou on 2017/7/31.
 */

public class AoPMagicConfig {

    public static void clearCache() {
        CacheAspect.getCache().evictAll();
    }

    public static void setResultCacheMaxSize(int size) {
        CacheAspect.setCache(new ExLruCache(size));
    }

    public static void setAsyncTaskExecutor(Executor executor) {
        AsyncAspect.setExecutor(executor);
    }

    public static void setDebugLogEnable(boolean enable) {
        LogAspect.enable = enable;
    }
}
