package com.dxn.wallpaperx.data.remote

import com.dxn.wallpaperx.domain.models.Wallpaper

interface RemoteRepository {
    suspend fun getWallpapers(page: Int, query: String): List<Wallpaper>
    suspend fun getWallpaper(id: String): Wallpaper
}