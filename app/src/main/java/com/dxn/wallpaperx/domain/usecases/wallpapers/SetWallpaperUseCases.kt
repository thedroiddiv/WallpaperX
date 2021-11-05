package com.dxn.wallpaperx.domain.usecases.wallpapers

import android.app.Application
import android.app.WallpaperManager
import android.content.Context
import android.util.Log
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.common.extensions.getBitmap
import javax.inject.Inject

class SetWallpaperUseCases
@Inject
constructor(private val context: Application) {
    private val wpm: WallpaperManager =
        context.getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager

    suspend operator fun invoke(wallpaper: Wallpaper, flag: Int) : Result<Boolean> {
        return kotlin.runCatching {
            val bitmap = context.getBitmap(wallpaper.wallpaperUrl)
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

    companion object {
        const val TAG = "SetWallpaperUseCases"
    }
}