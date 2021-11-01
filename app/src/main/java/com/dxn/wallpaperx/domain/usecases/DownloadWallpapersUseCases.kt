package com.dxn.wallpaperx.domain.usecases

import android.graphics.Bitmap
import android.util.Log
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import javax.inject.Inject

class DownloadWallpaper
@Inject
constructor(
    private val repository: WallpaperRepository
) {
    suspend operator fun invoke(bitmap: Bitmap, displayName: String) : Result<Boolean> {
       return try {
          repository.downloadWallpaper(bitmap, displayName)
           Result.success(true)
       } catch (e:Exception) {
           Log.e(TAG, "invoke: ${e.message}", )
           Result.failure(e)
       }
    }
    companion object {
        const val TAG = "DownloadWallpaper"
    }
}