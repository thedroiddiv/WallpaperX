package com.dxn.wallpaperx.domain.usecases

import android.app.Application
import android.app.WallpaperManager
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.extensions.getBitmap
import com.dxn.wallpaperx.extensions.shortToast
import javax.inject.Inject

class SetWallpaperUseCases
@Inject
constructor(private val context: Application) {
    private val wpm: WallpaperManager =
        context.getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager

    suspend operator fun invoke(wallpaper: Wallpaper, flag: Int) {
        Log.d(TAG, "invoke: setting wallpaper")
        val bitmap = context.getBitmap(wallpaper.wallpaperUrl)
        kotlin.runCatching {
            wpm.setBitmap(bitmap, null, true, flag)
        }.getOrElse {
            Log.e(TAG, "invoke: ${it.message}", )
            context.shortToast("Failure!")
        }
    }
    companion object{
        const val TAG = "SetWallpaperUseCases"
    }
}