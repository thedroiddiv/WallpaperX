package com.thedroiddiv.wallpaperx.data.remote.unsplash

import com.thedroiddiv.wallpaperx.data.model.Wallpaper
import com.thedroiddiv.wallpaperx.data.model.WallpaperCollection
import com.thedroiddiv.wallpaperx.data.remote.RemoteRepository
import com.thedroiddiv.wallpaperx.data.remote.buildHttpClient
import com.thedroiddiv.wallpaperx.data.remote.unsplash.dto.UnsplashCollectionDto
import com.thedroiddiv.wallpaperx.data.remote.unsplash.dto.UnsplashImageDto

class UnsplashRepository(apiKey: String) : RemoteRepository {

    private val api = UnsplashApi(
        buildHttpClient(
            baseUrl = BASE_URL,
            authParamName = AUTH_PARAM,
            authParamValue = apiKey,
        )
    )

    override suspend fun getWallpapers(page: Int, query: String): List<Wallpaper> =
        api.searchImages(query = query, page = page).results.map(::mapToWallpaper)

    override suspend fun getWallpaper(id: String): Wallpaper =
        mapToWallpaper(api.getImage(id))

    override suspend fun getCollections(page: Int): List<WallpaperCollection> =
        api.getCollections(page = page).map(::mapToCollection)

    override suspend fun getWallpapersByCollection(
        collectionId: String,
        page: Int,
    ): List<Wallpaper> =
        api.getPhotosByCollection(collectionId = collectionId, page = page).map(::mapToWallpaper)

    private fun mapToWallpaper(dto: UnsplashImageDto) = Wallpaper(
        id = dto.id,
        previewUrl = dto.urls.thumb,
        smallUrl = dto.urls.small,
        wallpaperUrl = dto.urls.full,
        user = dto.user.name,
        userImageUrl = dto.user.profileImage.small,
    )

    private fun mapToCollection(dto: UnsplashCollectionDto) = WallpaperCollection(
        id = dto.id,
        title = dto.title,
        totalPhotos = dto.totalPhotos,
        coverPhoto = dto.coverPhoto.urls.regular,
        tags = emptyList(),
    )

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"
        // Unsplash uses `client_id` as the auth query param name
        private const val AUTH_PARAM = "client_id"
    }
}
