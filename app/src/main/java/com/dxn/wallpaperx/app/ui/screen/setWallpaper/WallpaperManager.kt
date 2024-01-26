package com.dxn.wallpaperx.app.ui.screen.setWallpaper

import android.app.Application
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.domain.usecases.downloads.getBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WallpaperManager(private val context: Application) {
    private val wpm: WallpaperManager =
        context.getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager

    suspend fun setWallpaper(
        wallpaper: Wallpaper,
        flag: Int,
    ): Result<Boolean> {
        return kotlin.runCatching {
            val bitmap = context.getBitmap(wallpaper.wallpaperUrl)
            setWallpaper(bitmap, flag)
        }.getOrElse {
            Log.e(TAG, "invoke: ${it.message}")
            Result.failure(it)
        }
    }

    suspend fun setWallpaper(
        bitmap: Bitmap,
        flag: Int,
    ): Result<Boolean> {
        return withContext(Dispatchers.Default) {
            kotlin.runCatching {
                val res = wpm.setBitmap(bitmap, null, true, flag)
                if (res == 0) {
                    throw Throwable("Image processing failed!")
                }
                Result.success(true)
            }.getOrElse {
                Log.e(TAG, "invoke: ${it.message}")
                Result.failure(it)
            }
        }
    }

    companion object {
        const val TAG = "SetWallpaperUseCases"
    }
}
