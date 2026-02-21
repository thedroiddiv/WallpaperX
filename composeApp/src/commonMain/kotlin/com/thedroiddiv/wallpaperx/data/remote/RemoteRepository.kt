package com.thedroiddiv.wallpaperx.data.remote

import com.thedroiddiv.wallpaperx.data.model.Wallpaper
import com.thedroiddiv.wallpaperx.data.model.WallpaperCollection

interface RemoteRepository {
    suspend fun getWallpapers(page: Int, query: String): List<Wallpaper>
    suspend fun getWallpaper(id: String): Wallpaper
    suspend fun getCollections(page: Int): List<WallpaperCollection>
    suspend fun getWallpapersByCollection(collectionId: String, page: Int): List<Wallpaper>
}
