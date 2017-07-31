package com.smilehacker.aopmagic.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.smilehacker.aopmagic.async.Async;
import com.smilehacker.aopmagic.cache.CacheResult;
import com.smilehacker.aopmagic.safe.Safe;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cacheTest(1);
        cacheTest(2);
        cacheTest(1);

        Log.d("main thread", Thread.currentThread().getName());
        asyncTest();

        Log.d(TAG, "test exception = " + doException());
    }

    @CacheResult
    private int cacheTest(int p) {
        return p;
    }

    @Async
    private void asyncTest() {
        try {
            Thread.sleep(1000L);
            Log.d("async thread", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Safe
    private int doException() {
        int a = 1 / 0;
        return a;
    }
}
