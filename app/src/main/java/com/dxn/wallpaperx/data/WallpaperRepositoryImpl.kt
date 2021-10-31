package com.dxn.wallpaperx.data

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import com.dxn.wallpaperx.data.remote.PixabayApi
import com.dxn.wallpaperx.data.hitsToWallpapers
import com.dxn.wallpaperx.data.local.LocalRepository
import com.dxn.wallpaperx.data.local.favourites.FavouriteEntity
import com.dxn.wallpaperx.data.remote.RemoteRepository
import com.dxn.wallpaperx.domain.models.SavedWallpaper
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class WallpaperRepositoryImpl
constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : WallpaperRepository {

    companion object {
        const val TAG = "WallpaperRepositoryImpl"
    }

    override suspend fun getWallpapers(apiKey: String, page: Int, query: String) =
        remoteRepository.getWallpapers(apiKey, page, query)

    override suspend fun getWallpaper(apiKey: String, id: Int) =
        remoteRepository.getWallpaper(apiKey, id)

    override suspend fun saveWallpaper(savedWallpaper: SavedWallpaper): Boolean =
        localRepository.saveWallpaper(savedWallpaper)

    override suspend fun getSavedWallpapers(): List<SavedWallpaper> =
        localRepository.getSavedWallpapers()

    override suspend fun addFavourite(wallpaper: Wallpaper): Boolean =
        localRepository.addToFavourites(
            wallpaperToFavouriteEntity(wallpaper)
        )

    override suspend fun removeFavourite(id: Int): Boolean =
        localRepository.removeFavourite(id)

    override fun getFavourites(): Flow<List<Wallpaper>> {
        return localRepository.getFavourites()
            .map { wallpapers -> wallpapers.map { favEntityToWallpaper(it) } }
    }

}