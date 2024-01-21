package com.dxn.wallpaperx.app

import android.app.Application
import com.dxn.wallpaperx.app.di.appModule
import com.dxn.wallpaperx.data.dataModule
import com.dxn.wallpaperx.domain.wallpaperModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WallpaperApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WallpaperApplication)
            modules(dataModule, wallpaperModule, appModule)
        }
    }
}
