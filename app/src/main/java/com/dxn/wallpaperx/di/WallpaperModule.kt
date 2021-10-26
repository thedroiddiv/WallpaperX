package com.dxn.wallpaperx.di

import android.app.Application
import com.dxn.wallpaperx.data.remote.PixabayApi
import com.dxn.wallpaperx.data.repositories.WallpaperRepositoryImpl
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.dxn.wallpaperx.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WallpaperModule {

    @Provides
    @Singleton
    fun provideWallpaperUseCases(repository: WallpaperRepository,resourcesProvider: ResourcesProvider) = WallpaperUseCase(
        getWallpaper = GetWallpaper(repository),
        getWallpapers = GetWallpapers(repository,resourcesProvider),
        saveWallpaper = SaveWallpaper(repository),
        getSavedWallpapers = GetSavedWallpapers(repository),
        setWallpaper = SetWallpaper()
    )

    @Provides
    @Singleton
    fun provideWallpaperRepository(pixabayApi: PixabayApi,application: Application): WallpaperRepository =
        WallpaperRepositoryImpl(pixabayApi,application)
}