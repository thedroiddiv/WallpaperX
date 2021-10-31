package com.dxn.wallpaperx.data.local

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import com.dxn.wallpaperx.data.local.favourites.FavouriteEntity
import com.dxn.wallpaperx.data.local.favourites.FavouriteDao
import com.dxn.wallpaperx.domain.models.SavedWallpaper
import com.dxn.wallpaperx.domain.models.Wallpaper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import kotlin.math.log

class LocalRepository
@Inject
constructor(
    private val context: Application,
    private val favouriteDao: FavouriteDao
) {
    suspend fun saveWallpaper(savedWallpaper: SavedWallpaper): Boolean {
        return withContext(Dispatchers.IO) {
            runCatching {
                context.openFileOutput("${savedWallpaper.displayName}.jpg", Context.MODE_PRIVATE)
                    .use { stream ->
                        if (!savedWallpaper.bitmap.compress(
                                Bitmap.CompressFormat.JPEG,
                                95,
                                stream
                            )
                        ) {
                            throw IOException("Couldn't save bitmap.")
                        }
                    }
                true
            }.getOrThrow()
        }
    }

    suspend fun getSavedWallpapers(): List<SavedWallpaper> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val files = context.filesDir.listFiles()
                files?.filter { it.canRead() && it.isFile && it.name.endsWith(".jpg") }?.map {
                    val bytes = it.readBytes()
                    val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    SavedWallpaper(it.name, bmp)
                } ?: listOf()
            }.getOrThrow()
        }
    }

    suspend fun addToFavourites(id: FavouriteEntity): Boolean {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                favouriteDao.insert(id)
                true
            }.getOrThrow()
        }
    }

    fun getFavourites(): Flow<List<FavouriteEntity>> {
        return runCatching {
                favouriteDao.getAll()
            }.getOrThrow()
    }

    suspend fun removeFavourite(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                favouriteDao.delete(id)
                true
            }.getOrThrow()
        }
    }

    companion object {
        const val TAG = "LocalRepository"
    }

}