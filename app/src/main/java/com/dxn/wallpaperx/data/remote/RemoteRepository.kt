package com.dxn.wallpaperx.data.remote

import android.content.Context
import com.dxn.wallpaperx.data.hitsToWallpapers
import com.dxn.wallpaperx.domain.models.Wallpaper
import javax.inject.Inject

class RemoteRepository
@Inject
constructor(
    private val pixabayApi: PixabayApi
) {

    suspend fun getWallpapers(
        apiKey: String,
        page: Int,
        query: String
    ): List<Wallpaper> {
        return runCatching {
            val response = pixabayApi.getWallpapers(apikey = apiKey, page = page, query = query)
            hitsToWallpapers(response.hits)
        }.getOrThrow()
    }

    suspend fun getWallpaper(apiKey: String, id: Int): Wallpaper {
        return runCatching {
            val response = pixabayApi.getWallpaper(apikey = apiKey, imageId = id)
            hitsToWallpapers(response.hits)[0]
        }.getOrThrow()
    }
}