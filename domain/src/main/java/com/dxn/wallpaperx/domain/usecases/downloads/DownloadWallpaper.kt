package com.dxn.wallpaperx.domain.usecases.downloads

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import coil.ImageLoader
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.domain.repository.WallpaperRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DownloadWallpaper
@Inject
constructor(
    private val repository: WallpaperRepository,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(wallpaper: Wallpaper): Result<Uri?> {
        return try {
            val bitmap = context.getBitmap(wallpaper.wallpaperUrl)
            val uri = repository.downloadWallpaper(bitmap, "IMG${wallpaper.id}.jpg")
            Result.success(uri)
        } catch (e: Exception) {
            Log.e(TAG, "invoke: ${e.message}")
            Result.failure(e)
        }
    }

    companion object {
        const val TAG = "DownloadWallpaper"
    }
}

suspend fun Context.getBitmap(url: String): Bitmap {
    return withContext(Dispatchers.IO) {
        val loader = ImageLoader(this@getBitmap)
        val request = ImageRequest.Builder(this@getBitmap)
            .data(url)
            .allowHardware(false) // Disable hardware bitmaps.
            .build()
        val res = loader.execute(request)
        when (res) {
            is SuccessResult -> {
                val result = res.drawable
                (result as BitmapDrawable).bitmap
            }
            else -> {
                throw (res as ErrorResult).throwable
            }
        }
    }
}