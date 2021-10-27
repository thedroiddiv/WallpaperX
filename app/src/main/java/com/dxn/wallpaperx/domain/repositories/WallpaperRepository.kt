package com.dxn.wallpaperx.domain.repositories

import com.dxn.wallpaperx.domain.models.SavedWallpaper
import com.dxn.wallpaperx.domain.models.Wallpaper

interface WallpaperRepository {

    suspend fun getWallpapers(apiKey: String, page: Int, query: String): List<Wallpaper>
    suspend fun getWallpaper(apiKey: String, id: Int): Wallpaper

    suspend fun saveWallpaper(savedWallpaper: SavedWallpaper): Boolean
    suspend fun getSavedWallpapers(): List<SavedWallpaper>

    suspend fun addFavourite(wallpaper: Wallpaper): Boolean
    suspend fun removeFavourite(id: Int): Boolean
    suspend fun getFavourites(): List<Wallpaper>

}