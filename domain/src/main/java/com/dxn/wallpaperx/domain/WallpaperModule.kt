package com.dxn.wallpaperx.domain

import android.app.Application
import com.dxn.wallpaperx.data.local.LocalRepository
import com.dxn.wallpaperx.data.local.favourites.FavouriteDao
import com.dxn.wallpaperx.data.remote.RemoteRepository
import com.dxn.wallpaperx.data.remote.pixabay.PixabayApi
import com.dxn.wallpaperx.data.remote.pixabay.PixabayRepository
import com.dxn.wallpaperx.data.remote.unsplash.UnsplashApi
import com.dxn.wallpaperx.data.remote.unsplash.UnsplashRepository
import com.dxn.wallpaperx.domain.repository.WallpaperRepository
import com.dxn.wallpaperx.domain.repository.WallpaperRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WallpaperModule {

    @Provides
    @Singleton
    @Named("unsplash")
    fun provideUnsplashRepository(
        unsplashApi: UnsplashApi
    ): RemoteRepository =
        UnsplashRepository(unsplashApi)

    @Provides
    @Singleton
    @Named("pixabay")
    fun providePixabayRepo(pixabayApi: PixabayApi): RemoteRepository =
        PixabayRepository(pixabayApi)

    @Provides
    @Singleton
    fun provideLocalRepository(
        context: Application,
        favouriteDao: FavouriteDao
    ): LocalRepository = LocalRepository(context, favouriteDao)

    @Provides
    @Singleton
    fun provideWallpaperRepository(
        @Named("pixabay") remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ): WallpaperRepository =
        WallpaperRepositoryImpl(remoteRepository, localRepository)
}

val wallpaperModule = module {
    single<RemoteRepository> { PixabayRepository(get()) }
    single<LocalRepository> { LocalRepository(androidApplication(), get()) }
    single<WallpaperRepository> { WallpaperRepositoryImpl(get(), get()) }
}
