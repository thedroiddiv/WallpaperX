package com.dxn.wallpaperx.data.remote.pixabay

import com.dxn.wallpaperx.data.BuildConfig
import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.data.remote.RemoteRepository
import com.dxn.wallpaperx.data.remote.pixabay.models.Hit

class PixabayRepository(private val api: PixabayApi) : RemoteRepository {

    private val apiKey: String = BuildConfig.PIXABAY_API_KEY

    override suspend fun getWallpapers(page: Int, query: String): List<Wallpaper> {
        return api.searchImage(
            apiKey = apiKey,
            query = query,
            page = page
        ).hits.map(::hitToWallpaper)
    }

    override suspend fun getWallpaper(id: String): Wallpaper {
        return api.searchImage(
            apiKey = apiKey,
            id = id
        ).hits.map(::hitToWallpaper).first()
    }

    override suspend fun getCollections(page: Int): List<Collection> {
        return if (page == 1) collections else listOf()
    }

    override suspend fun getWallpapersByCollection(
        collectionId: String,
        page: Int
    ): List<Wallpaper> {
        return api.searchImage(
            apiKey = apiKey,
            category = collectionId
        ).hits.map(::hitToWallpaper)
    }
}

fun hitToWallpaper(imageDto: Hit): Wallpaper {
    return Wallpaper(
        id = imageDto.id.toString(),
        previewUrl = imageDto.previewURL,
        smallUrl = imageDto.webformatURL,
        wallpaperUrl = imageDto.largeImageURL,
        user = imageDto.user,
        userImageURL = imageDto.userImageURL
    )
}
