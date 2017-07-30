package com.smilehacker.aopmagic.cache;

import android.util.LruCache;

/**
 * Created by zhouquan on 17/7/24.
 */

public class ExLruCache extends LruCache<String, Object> {

    /**
     *
     * @param maxSize num of object
     */
    public ExLruCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Object value) {
        return 1;
    }
}
