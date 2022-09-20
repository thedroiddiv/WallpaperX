package com.dxn.wallpaperx.data

import android.app.Application
import com.dxn.wallpaperx.data.local.LocalDatabase
import com.dxn.wallpaperx.data.remote.unsplash.UnsplashApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/* This class can be moved to data, but will increase dependency in that module  */

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideFavouritesDao(application: Application) =
        LocalDatabase.getNoteDatabase(application).getNoteDao()

    @Provides
    @Singleton
    fun provideUnsplashApi(): UnsplashApi = Retrofit.Builder().baseUrl("https://api.unsplash.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(UnsplashApi::class.java)
}
