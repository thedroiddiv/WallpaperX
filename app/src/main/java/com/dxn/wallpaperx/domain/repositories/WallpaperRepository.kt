package com.dxn.wallpaperx.domain.repositories

import com.dxn.wallpaperx.domain.models.Wallpaper
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {
    suspend fun getWallpapers(query:String) : Flow<List<Wallpaper>>;
    suspend fun getWallpaper(id:Int) : Wallpaper;
    suspend fun getDownloadedWallpapers() : Flow<List<Wallpaper>>;
    suspend fun getFavourites():Flow<List<Int>>;
}