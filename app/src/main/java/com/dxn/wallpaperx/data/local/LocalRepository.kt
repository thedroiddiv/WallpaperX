package com.dxn.wallpaperx.data.local

import android.app.Application
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.dxn.wallpaperx.data.local.favourites.FavouriteDao
import com.dxn.wallpaperx.data.local.favourites.FavouriteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject

class LocalRepository
@Inject
constructor(
    private val context: Application,
    private val favouriteDao: FavouriteDao
) {
    suspend fun downloadWallpaper(bitmap: Bitmap, displayName: String): Uri? {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val imageUri: Uri?
                var fos: OutputStream?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    context.contentResolver.let { resolver ->
                        val contentValues = ContentValues().apply {
                            put(MediaStore.Images.ImageColumns.DISPLAY_NAME, displayName)
                            put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/jpeg")
                            put(
                                MediaStore.Images.ImageColumns.RELATIVE_PATH,
                                Environment.DIRECTORY_PICTURES + "/wallpaperx"
                            )
                        }
                        imageUri = resolver.insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            contentValues
                        )
                        fos = imageUri?.let { context.contentResolver.openOutputStream(it) }
                    }
                } else {
                    val imagesDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/wallpaperx")
                    val image = File(imagesDir, displayName)
                    fos = FileOutputStream(image)
                    imageUri = Uri.fromFile(image)
                }
                fos?.use {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 95, it)
                    it.close()
                }
                imageUri
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

    suspend fun removeFavourite(id: String): Boolean {
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