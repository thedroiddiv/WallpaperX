package com.dxn.wallpaperx.data.repositories

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.dxn.wallpaperx.data.remote.PixabayApi
import com.dxn.wallpaperx.data.hitsToWallpapers
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import java.io.IOException
import java.lang.Exception

class WallpaperRepositoryImpl
constructor(
    private val pixabayApi: PixabayApi,
    private val context : Context
) : WallpaperRepository {

    companion object {
        const val TAG = "WallpaperRepositoryImpl"
    }

    override suspend fun getWallpapers(apiKey: String, page: Int, query: String): List<Wallpaper> {
        try {
            val response = pixabayApi.getWallpapers(apikey = apiKey, page = page, query = query)
            return hitsToWallpapers(response.hits)
        } catch (e: Exception) {
            Log.e(TAG, "getWallpapers: ${e.message}")
        }
        return emptyList()
    }

    override suspend fun getWallpaper(apiKey: String,id: Int): Wallpaper {
        try {
            val response = pixabayApi.getWallpaper(apikey = apiKey,imageId = id)
            return hitsToWallpapers(response.hits)[0]
        } catch (e: Exception) {
            Log.e(TAG, "getWallpapers: ${e.message}")
            throw e;
        }
    }

    override suspend fun saveWallpaper(bitmap: Bitmap, displayName:String): Uri {
        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
            }
        }
        var uri: Uri? = null
        return runCatching {
            with(context.contentResolver) {
                insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)?.also {
                    uri = it // Keep uri reference so it can be removed on failure
                    openOutputStream(it)?.use { stream ->
                        if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream))
                            throw IOException("Failed to save bitmap.")
                    } ?: throw IOException("Failed to open output stream.")

                } ?: throw IOException("Failed to create new MediaStore record.")
            }
        }.getOrElse {
            uri?.let { orphanUri ->
                // Don't leave an orphan entry in the MediaStore
                context.contentResolver.delete(orphanUri, null, null)
            }
            throw it
        }
    }

    override suspend fun getDownloadedWallpapers(): List<Wallpaper> {
        return emptyList()
    }

    override suspend fun addToFavourites(id: Int): Boolean {
        return true
    }

    override suspend fun getFavourites(): List<Int> {
        return emptyList()
    }
}