package com.example.youtubeapi

import android.app.Application
import com.example.youtubeapi.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class YoutubeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@YoutubeApp)
            modules(appModule)
        }
    }
}