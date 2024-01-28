package com.dxn.wallpaperx.data

import com.dxn.wallpaperx.data.local.LocalDatabase
import com.dxn.wallpaperx.data.local.dao.FavouriteDao
import com.dxn.wallpaperx.data.remote.pixabay.PixabayApi
import com.dxn.wallpaperx.data.remote.unsplash.UnsplashApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
