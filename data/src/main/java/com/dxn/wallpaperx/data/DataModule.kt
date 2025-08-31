package com.dxn.wallpaperx.data

import android.app.Application
import com.dxn.wallpaperx.data.local.LocalDatabase
import com.dxn.wallpaperx.data.remote.pixabay.PixabayApi
import com.dxn.wallpaperx.data.remote.unsplash.UnsplashApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return logging
    }

    @Provides
    @Singleton
    fun provideUnsplashApi(
        loggingInterceptor: HttpLoggingInterceptor
    ): UnsplashApi = Retrofit.Builder().baseUrl("https://api.unsplash.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val req = chain.request()
                    val newUrl = req.url.newBuilder()
                        .addQueryParameter("client_id", BuildConfig.UNSPLASH_API_KEY)
                        .build()
                    val newReq = req.newBuilder().url(newUrl).build()
                    chain.proceed(newReq)
                }
                .addInterceptor(loggingInterceptor)
                .build()
        )
        .build()
        .create(UnsplashApi::class.java)

    @Provides
    @Singleton
    fun providePixabayApi(
        loggingInterceptor: HttpLoggingInterceptor
    ): PixabayApi {
        return Retrofit.Builder().baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val req = chain.request()
                        val newUrl = req.url.newBuilder()
                            .addQueryParameter("key", BuildConfig.PIXABAY_API_KEY)
                            .build()
                        val newReq = req.newBuilder().url(newUrl).build()
                        chain.proceed(newReq)
                    }
                    .addInterceptor(loggingInterceptor)
                    .build(),
            )
            .build()
            .create(PixabayApi::class.java)
    }
}
