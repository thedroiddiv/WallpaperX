package com.dxn.wallpaperx.domain.repositories

import android.graphics.Bitmap
import com.dxn.wallpaperx.domain.models.Wallpaper
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {

    suspend fun getWallpapers(page: Int, query: String): List<Wallpaper>
    suspend fun getWallpaper(id: String): Wallpaper

    suspend fun downloadWallpaper(bitmap: Bitmap, displayName : String)

    suspend fun addFavourite(wallpaper: Wallpaper): Boolean
    suspend fun removeFavourite(id: String): Boolean

    fun getFavourites(): Flow<List<Wallpaper>>

}