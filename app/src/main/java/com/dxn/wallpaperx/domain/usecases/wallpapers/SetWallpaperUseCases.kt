package com.dxn.wallpaperx.domain.usecases.wallpapers

import android.app.Application
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.common.extensions.getBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetWallpaperUseCases
@Inject
constructor(private val context: Application) {
    private val wpm: WallpaperManager =
        context.getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager

    suspend operator fun invoke(wallpaper: Wallpaper, flag: Int): Result<Boolean> {
        return kotlin.runCatching {
            val bitmap = context.getBitmap(wallpaper.wallpaperUrl)
            invoke(bitmap, flag)
        }.getOrElse {
            Log.e(TAG, "invoke: ${it.message}")
            Result.failure(it)
        }
    }

    suspend operator fun invoke(bitmap: Bitmap, flag: Int): Result<Boolean> {
        return withContext(Dispatchers.Default) {
            kotlin.runCatching {
                val res = wpm.setBitmap(bitmap, null, true, flag)
                Log.d(TAG, "invoke: $res")
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