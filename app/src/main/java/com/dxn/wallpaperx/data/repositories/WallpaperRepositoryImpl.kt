package com.dxn.wallpaperx.data.repositories

import android.util.Log
import com.dxn.wallpaperx.data.source.PixabayApi
import com.dxn.wallpaperx.data.util.hitsToWallpapers
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log

class WallpaperRepositoryImpl
constructor(
    private val pixabayApi: PixabayApi
) : WallpaperRepository {

    companion object {
        const val TAG = "WallpaperRepositoryImpl"
    }

    override suspend fun getWallpapers(apiKey:String,page: Int, query: String): List<Wallpaper> {
        try {
            val response = pixabayApi.getWallpapers(apikey = apiKey, page = page, query = query)
            return hitsToWallpapers(response.hits)
        } catch (e: Exception) {
            Log.e(TAG, "getWallpapers: ${e.message}")
        }
        return emptyList()
    }

    override suspend fun getWallpaper(id: Int): Wallpaper {
        return Wallpaper(5112,"","")
    }

    override suspend fun getDownloadedWallpapers(): List<Wallpaper> {
        return emptyList()
    }

    override suspend fun getFavourites(): List<Int> {
        return emptyList()
    }
}