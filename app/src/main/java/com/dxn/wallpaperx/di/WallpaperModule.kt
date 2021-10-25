package com.dxn.wallpaperx.di

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
        downloadWallpaper = DownloadWallpaper(repository),
        getDownloadedWallpapers = GetDownloadedWallpapers(repository),
        setWallpaper = SetWallpaper()
    )



}