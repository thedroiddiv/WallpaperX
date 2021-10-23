package com.dxn.wallpaperx.data.repositories

import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import kotlinx.coroutines.flow.Flow

class WallpaperRepositoryImpl : WallpaperRepository {
    override suspend fun getWallpapers(query: String): Flow<List<Wallpaper>> {
        TODO("Not yet implemented")
    }

    override suspend fun getWallpaper(id: Int): Wallpaper {
        TODO("Not yet implemented")
    }

    override suspend fun getDownloadedWallpapers(): Flow<List<Wallpaper>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavourites(): Flow<List<Int>> {
        TODO("Not yet implemented")
    }
}