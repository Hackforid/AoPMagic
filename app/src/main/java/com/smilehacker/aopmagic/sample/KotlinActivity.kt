package com.smilehacker.aopmagic.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.smilehacker.aopmagic.cache.CacheResult
import com.smilehacker.aopmagic.log.DebugLog

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        timeFoo(100)
        doCacheInKotlin()
    }

    @CacheResult
    private fun doCacheInKotlin() : Int {
        return 100
    }

    @DebugLog
    private fun timeFoo(aa : Int) : String {
        return aa.toString()
    }
}
