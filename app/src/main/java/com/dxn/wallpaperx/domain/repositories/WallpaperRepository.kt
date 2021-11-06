package com.dxn.wallpaperx.domain.repositories

import android.graphics.Bitmap
import android.net.Uri
import com.dxn.wallpaperx.domain.models.Collection
import com.dxn.wallpaperx.domain.models.Wallpaper
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {

    suspend fun getWallpapers(page: Int, query: String): List<Wallpaper>
    suspend fun getWallpaper(id: String): Wallpaper

    suspend fun getCollections(page: Int): List<Collection>
    suspend fun getWallpapersByCollection(collectionId: String, page: Int): List<Wallpaper>

    suspend fun downloadWallpaper(bitmap: Bitmap, displayName: String) : Uri?

    suspend fun addFavourite(wallpaper: Wallpaper): Boolean
    suspend fun removeFavourite(id: String): Boolean

    fun getFavourites(): Flow<List<Wallpaper>>

}