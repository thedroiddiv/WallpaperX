package com.dxn.wallpaperx.data.remote.pixabay

import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.common.helpers.ResourcesProvider
import com.dxn.wallpaperx.data.hitsToWallpapers
import com.dxn.wallpaperx.data.remote.RemoteRepository
import com.dxn.wallpaperx.domain.models.Wallpaper
import javax.inject.Inject

class PixabayRepository
constructor(
    private val pixabayApi: PixabayApi,
    resourcesProvider: ResourcesProvider
) : RemoteRepository {

    private val apikey = resourcesProvider.getString(R.string.pixabay_api_key)

    override suspend fun getWallpapers(
        page: Int,
        query: String
    ): List<Wallpaper> {
        return runCatching {
            val response = pixabayApi.getWallpapers(apikey = apikey, page = page, query = query)
            hitsToWallpapers((response.hits))
        }.getOrThrow()
    }

    override suspend fun getWallpaper(id: String): Wallpaper {
        return runCatching {
            val response = pixabayApi.getWallpaper(apikey = apikey, imageId = id.toInt())
            hitsToWallpapers(response.hits)[0]
        }.getOrThrow()
    }
}