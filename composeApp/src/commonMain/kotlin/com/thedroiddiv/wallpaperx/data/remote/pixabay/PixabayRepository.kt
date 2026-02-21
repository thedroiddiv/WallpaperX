package com.thedroiddiv.wallpaperx.data.remote.pixabay

import com.thedroiddiv.wallpaperx.data.model.Wallpaper
import com.thedroiddiv.wallpaperx.data.model.WallpaperCollection
import com.thedroiddiv.wallpaperx.data.remote.RemoteRepository
import com.thedroiddiv.wallpaperx.data.remote.buildHttpClient
import com.thedroiddiv.wallpaperx.data.remote.pixabay.dto.PixabayHit

class PixabayRepository(apiKey: String) : RemoteRepository {

    private val api = PixabayApi(
        buildHttpClient(
            baseUrl = BASE_URL,
            authParamName = AUTH_PARAM,
            authParamValue = apiKey,
        )
    )

    override suspend fun getWallpapers(page: Int, query: String): List<Wallpaper> =
        api.searchImages(query = query, page = page).hits.map(::mapToWallpaper)

    override suspend fun getWallpaper(id: String): Wallpaper =
        api.searchImages(id = id).hits.map(::mapToWallpaper).first()

    override suspend fun getCollections(page: Int): List<WallpaperCollection> =
        if (page == 1) pixabayCollections else emptyList()

    override suspend fun getWallpapersByCollection(
        collectionId: String,
        page: Int,
    ): List<Wallpaper> =
        api.searchImages(category = collectionId, page = page).hits.map(::mapToWallpaper)

    private fun mapToWallpaper(hit: PixabayHit) = Wallpaper(
        id = hit.id.toString(),
        previewUrl = hit.previewURL,
        smallUrl = hit.webformatURL,
        wallpaperUrl = hit.largeImageURL,
        user = hit.user,
        userImageUrl = hit.userImageURL,
    )

    companion object {
        private const val BASE_URL = "https://pixabay.com/"
        private const val AUTH_PARAM = "key"
    }
}
