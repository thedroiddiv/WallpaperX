package com.dxn.wallpaperx.domain.usecases

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.dxn.wallpaperx.common.extensions.getBitmap
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DownloadWallpaper
@Inject
constructor(
    private val repository: WallpaperRepository,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(wallpaper: Wallpaper): Result<Boolean> {
        return try {
            val bitmap = context.getBitmap(wallpaper.wallpaperUrl)
            repository.downloadWallpaper(bitmap, "IMG${wallpaper.id}.jpg")
            Result.success(true)
        } catch (e: Exception) {
            Log.e(TAG, "invoke: ${e.message}")
            Result.failure(e)
        }
    }

    companion object {
        const val TAG = "DownloadWallpaper"
    }
}