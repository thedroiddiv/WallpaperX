package com.dxn.wallpaperx.domain.usecases

import android.graphics.Bitmap
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository

class SaveWallpaper(private val repository: WallpaperRepository) {
    suspend operator fun invoke(bitmap: Bitmap, displayName:String) {
        repository.saveWallpaper(bitmap,displayName)
    }
}