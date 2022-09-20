package com.dxn.wallpaperx.data.remote

import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.data.model.Wallpaper

interface RemoteRepository {
    suspend fun getWallpapers(page: Int, query: String): List<Wallpaper>
    suspend fun getWallpaper(id: String): Wallpaper
    suspend fun getCollections(page: Int): List<Collection>
    suspend fun getWallpapersByCollection(collectionId: String, page: Int): List<Wallpaper>
}
