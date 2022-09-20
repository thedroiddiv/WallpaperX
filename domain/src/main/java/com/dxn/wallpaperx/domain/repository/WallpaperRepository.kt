package com.dxn.wallpaperx.domain.repository

import android.graphics.Bitmap
import android.net.Uri
import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.data.model.Wallpaper
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {

    suspend fun getWallpapers(page: Int, query: String): List<Wallpaper>
    suspend fun getWallpaper(id: String): Wallpaper

    suspend fun getCollections(page: Int): List<Collection>
    suspend fun getWallpapersByCollection(collectionId: String, page: Int): List<Wallpaper>

    suspend fun downloadWallpaper(bitmap: Bitmap, displayName: String): Uri?

    suspend fun addFavourite(wallpaper: Wallpaper): Boolean
    suspend fun removeFavourite(id: String): Boolean

    fun getFavourites(): Flow<List<Wallpaper>>
}
