package com.zhadko.topredditpostsviewer

import android.app.Application
import com.zhadko.topredditpostsviewer.di.authModule
import com.zhadko.topredditpostsviewer.di.dataModule
import com.zhadko.topredditpostsviewer.di.networkModule
import com.zhadko.topredditpostsviewer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(dataModule, viewModelModule, networkModule, authModule))
        }
    }

}