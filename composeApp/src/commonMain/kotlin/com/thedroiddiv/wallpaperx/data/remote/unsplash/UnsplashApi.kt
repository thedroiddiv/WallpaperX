package com.thedroiddiv.wallpaperx.data.remote.unsplash

import com.thedroiddiv.wallpaperx.data.model.Wallpaper
import com.thedroiddiv.wallpaperx.data.model.WallpaperCollection
import com.thedroiddiv.wallpaperx.data.remote.WallpaperApi
import com.thedroiddiv.wallpaperx.data.remote.buildHttpClient
import com.thedroiddiv.wallpaperx.data.remote.unsplash.dto.UnsplashCollectionDto
import com.thedroiddiv.wallpaperx.data.remote.unsplash.dto.UnsplashImageDto
import com.thedroiddiv.wallpaperx.data.remote.unsplash.dto.UnsplashSearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class UnsplashApi(apiKey: String) : WallpaperApi {

    private val client: HttpClient = buildHttpClient(
        baseUrl = BASE_URL,
        authParamName = AUTH_PARAM,
        authParamValue = apiKey,
    )

    override suspend fun getWallpapers(page: Int, query: String): List<Wallpaper> =
        fetchImages(query = query, page = page).results.map(::toWallpaper)

    override suspend fun getWallpaper(id: String): Wallpaper =
        toWallpaper(fetchImage(id))

    override suspend fun getCollections(page: Int): List<WallpaperCollection> =
        fetchCollections(page = page).map(::toCollection)

    override suspend fun getWallpapersByCollection(
        collectionId: String,
        page: Int,
    ): List<Wallpaper> = fetchCollectionPhotos(collectionId = collectionId, page = page).map(::toWallpaper)

    // ── HTTP ─────────────────────────────────────────────────────────────────────

    private suspend fun fetchImages(
        query: String,
        page: Int = 1,
        perPage: Int = 20,
    ) = client.get("/search/photos") {
        parameter("query", query)
        parameter("page", page)
        parameter("orientation", "portrait")
        parameter("per_page", perPage)
    }.body<UnsplashSearchResponse>()

    private suspend fun fetchCollections(
        page: Int = 1,
        perPage: Int = 20,
    ): List<UnsplashCollectionDto> = client.get("/collections") {
        parameter("page", page)
        parameter("per_page", perPage)
    }.body()

    private suspend fun fetchCollectionPhotos(
        collectionId: String,
        page: Int = 1,
        perPage: Int = 20,
    ): List<UnsplashImageDto> = client.get("/collections/$collectionId/photos") {
        parameter("page", page)
        parameter("per_page", perPage)
    }.body()

    private suspend fun fetchImage(id: String): UnsplashImageDto =
        client.get("/photos/$id").body()

    // ── Mappers ──────────────────────────────────────────────────────────────────

    private fun toWallpaper(dto: UnsplashImageDto) = Wallpaper(
        id = dto.id,
        previewUrl = dto.urls.thumb,
        smallUrl = dto.urls.small,
        wallpaperUrl = dto.urls.full,
        user = dto.user.name,
        userImageUrl = dto.user.profileImage.small,
    )

    private fun toCollection(dto: UnsplashCollectionDto) = WallpaperCollection(
        id = dto.id,
        title = dto.title,
        totalPhotos = dto.totalPhotos,
        coverPhoto = dto.coverPhoto.urls.regular,
        tags = emptyList(),
    )

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"
        private const val AUTH_PARAM = "client_id"
    }
}
