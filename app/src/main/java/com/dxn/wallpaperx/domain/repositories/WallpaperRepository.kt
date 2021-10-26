package com.dxn.wallpaperx.domain.repositories

import android.graphics.Bitmap
import android.net.Uri
import com.dxn.wallpaperx.domain.models.Wallpaper
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {
    suspend fun getWallpapers(apiKey:String,page:Int,query:String) : List<Wallpaper>;
    suspend fun getWallpaper(apiKey: String,id: Int) : Wallpaper;

    suspend fun saveWallpaper(bitmap: Bitmap, displayName:String) : Uri ;
    suspend fun getDownloadedWallpapers() : List<Wallpaper>;

    suspend fun addToFavourites(id:Int) : Boolean;
    suspend fun getFavourites():List<Int>;

}