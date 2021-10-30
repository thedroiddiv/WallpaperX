package com.dxn.wallpaperx.domain.usecases

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import com.dxn.wallpaperx.domain.models.SavedWallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.dxn.wallpaperx.extensions.downloadWallpaper
import javax.inject.Inject

class DownloadWallpaper
@Inject
constructor(private val application: Application) {
    suspend operator fun invoke(bitmap: Bitmap, displayName: String) {
        kotlin.runCatching {
            application.downloadWallpaper(bitmap,displayName)
        }.getOrElse {
            Log.e(TAG, "invoke: ${it.message}" )
        }
    }
    companion object {
        const val TAG = "DownloadWallpaper"
    }
}