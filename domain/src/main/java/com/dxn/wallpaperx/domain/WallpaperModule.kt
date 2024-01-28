package com.dxn.wallpaperx.domain

import com.dxn.wallpaperx.data.local.LocalRepository
import com.dxn.wallpaperx.data.remote.RemoteRepository
import com.dxn.wallpaperx.data.remote.pixabay.PixabayRepository
import com.dxn.wallpaperx.domain.repository.WallpaperRepository
import com.dxn.wallpaperx.domain.repository.WallpaperRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val wallpaperModule =
    module {
        single<RemoteRepository> { PixabayRepository(get(), get()) }
        single<LocalRepository> { LocalRepository(androidApplication(), get()) }
        single<WallpaperRepository> { WallpaperRepositoryImpl(get(), get()) }
    }
