package com.dxn.wallpaperx.domain.repositories

import com.dxn.wallpaperx.domain.models.Wallpaper
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {
    suspend fun getWallpapers(apiKey:String,page:Int,query:String) : List<Wallpaper>;
    suspend fun getWallpaper(id:Int) : Wallpaper;
    suspend fun getDownloadedWallpapers() : List<Wallpaper>;
    suspend fun getFavourites():List<Int>;
}