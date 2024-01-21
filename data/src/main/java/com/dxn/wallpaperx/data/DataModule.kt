package com.dxn.wallpaperx.data

import android.app.Application
import com.dxn.wallpaperx.data.local.LocalDatabase
import com.dxn.wallpaperx.data.local.favourites.FavouriteDao
import com.dxn.wallpaperx.data.remote.pixabay.PixabayApi
import com.dxn.wallpaperx.data.remote.unsplash.UnsplashApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// This class can be moved to data, but will increase dependency in that module

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideFavouritesDao(application: Application) = LocalDatabase.getNoteDatabase(application).getNoteDao()

    @Provides
    @Singleton
    fun provideUnsplashApi(): UnsplashApi =
        Retrofit.Builder().baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val req = chain.request()
                        val newUrl =
                            req.url.newBuilder()
                                .addQueryParameter("client_id", BuildConfig.UNSPLASH_API_KEY)
                                .build()
                        val newReq = req.newBuilder().url(newUrl).build()
                        chain.proceed(newReq)
                    }
                    .build(),
            )
            .build()
            .create(UnsplashApi::class.java)

    @Provides
    @Singleton
    fun providePixabayApi(): PixabayApi =
        Retrofit.Builder().baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        },
                    )
                    .build(),
            )
            .build()
            .create(PixabayApi::class.java)
}

val dataModule =
    module {
        single<FavouriteDao> { LocalDatabase.getNoteDatabase(androidContext()).getNoteDao() }

        single<UnsplashApi> {
            Retrofit.Builder().baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val req = chain.request()
                            val newUrl =
                                req.url.newBuilder()
                                    .addQueryParameter("client_id", BuildConfig.UNSPLASH_API_KEY)
                                    .build()
                            val newReq = req.newBuilder().url(newUrl).build()
                            chain.proceed(newReq)
                        }
                        .build(),
                )
                .build()
                .create(UnsplashApi::class.java)
        }

        single<PixabayApi> {
            Retrofit.Builder().baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor().apply {
                                setLevel(HttpLoggingInterceptor.Level.BODY)
                            },
                        )
                        .build(),
                )
                .build()
                .create(PixabayApi::class.java)
        }
    }
