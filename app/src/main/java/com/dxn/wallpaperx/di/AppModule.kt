package com.dxn.wallpaperx.di

import com.dxn.wallpaperx.data.repositories.WallpaperRepositoryImpl
import com.dxn.wallpaperx.data.source.PixabayApi
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePixabayApi(): PixabayApi = Retrofit.Builder().baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(PixabayApi::class.java)

    @Provides
    @Singleton
    fun provideWallpaperRepository(pixabayApi: PixabayApi): WallpaperRepository =
        WallpaperRepositoryImpl(pixabayApi)


}