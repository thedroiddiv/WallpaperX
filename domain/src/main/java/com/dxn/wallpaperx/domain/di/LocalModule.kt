package com.dxn.wallpaperx.domain.di

import android.app.Application
import com.dxn.wallpaperx.data.local.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {
    @Provides
    @Singleton
    fun provideFavouritesDao(application: Application) =
        LocalDatabase.getNoteDatabase(application).getNoteDao()
}