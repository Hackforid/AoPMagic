package com.smilehacker.aopmagic.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smilehacker.aopmagic.cache.CacheResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cacheTest(1);
        cacheTest(2);
        cacheTest(1);
    }

    @CacheResult
    private int cacheTest(int p) {
        return p;
    }
}
