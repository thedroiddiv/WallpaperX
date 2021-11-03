package com.dxn.wallpaperx.di

import com.dxn.wallpaperx.common.helpers.ResourcesProvider
import com.dxn.wallpaperx.data.local.LocalRepository
import com.dxn.wallpaperx.data.remote.RemoteRepository
import com.dxn.wallpaperx.data.WallpaperRepositoryImpl
import com.dxn.wallpaperx.data.remote.pixabay.PixabayApi
import com.dxn.wallpaperx.data.remote.pixabay.PixabayRepository
import com.dxn.wallpaperx.data.remote.unsplash.UnsplashApi
import com.dxn.wallpaperx.data.remote.unsplash.UnsplashRepository
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WallpaperModule {

    @Provides
    @Singleton
    @Named("unsplash")
    fun provideUnsplashRepository(
        unsplashApi: UnsplashApi,
        resourcesProvider: ResourcesProvider
    ): RemoteRepository =
        UnsplashRepository(unsplashApi, resourcesProvider)

    @Provides
    @Singleton
    @Named("pixabay")
    fun providePixabayRepository(
        api: PixabayApi,
        resourcesProvider: ResourcesProvider
    ): RemoteRepository =
        PixabayRepository(api, resourcesProvider)

    @Provides
    @Singleton
    fun provideWallpaperRepository(
        @Named("unsplash") remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ): WallpaperRepository =
        WallpaperRepositoryImpl(remoteRepository, localRepository)
}