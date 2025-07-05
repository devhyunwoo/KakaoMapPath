package com.example.kakaomappath

import android.app.Application
import com.kakao.vectormap.Const
import com.kakao.vectormap.KakaoMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoMapSdk.init(this, Constants.NATIVE_APP_KEY);
    }
}