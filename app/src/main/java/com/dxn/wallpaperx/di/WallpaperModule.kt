package com.dxn.wallpaperx.di

import com.dxn.wallpaperx.data.local.LocalRepository
import com.dxn.wallpaperx.data.remote.RemoteRepository
import com.dxn.wallpaperx.data.WallpaperRepositoryImpl
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.dxn.wallpaperx.domain.usecases.*
import com.dxn.wallpaperx.helpers.ResourcesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WallpaperModule {

//    @Provides
//    @Singleton
//    fun provideWallpaperUseCases(
//        repository: WallpaperRepository,
//        resourcesProvider: ResourcesProvider
//    ) = WallpaperUseCase(
//        getWallpaper = GetWallpaper(repository, resourcesProvider),
//        getWallpapers = GetWallpapers(repository, resourcesProvider),
//        saveWallpaper = SaveWallpaper(repository),
//        getSavedWallpapers = GetSavedWallpapers(repository),
//        setWallpaper = SetWallpaper(),
//        getFavourites = GetFavourites(repository),
//        addFavourite = AddFavourite(repository),
//        removeFavourite = RemoveFavourite(repository)
//    )

    @Provides
    @Singleton
    fun provideWallpaperRepository(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ): WallpaperRepository =
        WallpaperRepositoryImpl(remoteRepository, localRepository)
}