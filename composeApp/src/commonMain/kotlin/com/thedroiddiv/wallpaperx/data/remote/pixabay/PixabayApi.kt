package com.thedroiddiv.wallpaperx.data.remote.pixabay

import com.thedroiddiv.wallpaperx.data.model.Wallpaper
import com.thedroiddiv.wallpaperx.data.model.WallpaperCollection
import com.thedroiddiv.wallpaperx.data.remote.WallpaperApi
import com.thedroiddiv.wallpaperx.data.remote.buildHttpClient
import com.thedroiddiv.wallpaperx.data.remote.pixabay.dto.PixabayHit
import com.thedroiddiv.wallpaperx.data.remote.pixabay.dto.PixabaySearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PixabayApi(apiKey: String) : WallpaperApi {

    private val client: HttpClient = buildHttpClient(
        baseUrl = BASE_URL,
        authParamName = AUTH_PARAM,
        authParamValue = apiKey,
    )

    override suspend fun getWallpapers(page: Int, query: String): List<Wallpaper> =
        search(query = query, page = page).hits.map(::toWallpaper)

    override suspend fun getWallpaper(id: String): Wallpaper =
        search(id = id).hits.map(::toWallpaper).first()

    override suspend fun getCollections(page: Int): List<WallpaperCollection> =
        if (page == 1) pixabayCollections else emptyList()

    override suspend fun getWallpapersByCollection(
        collectionId: String,
        page: Int,
    ): List<Wallpaper> = search(category = collectionId, page = page).hits.map(::toWallpaper)

    // ── HTTP ─────────────────────────────────────────────────────────────────────

    private suspend fun search(
        query: String? = null,
        page: Int = 1,
        orientation: String = "vertical",
        category: String? = null,
        id: String? = null,
        imageType: String? = null,
    ): PixabaySearchResponse = client.get("api") {
        parameter("page", page)
        parameter("lang", "en")
        parameter("orientation", orientation)
        query?.let { parameter("q", it) }
        id?.let { parameter("id", it) }
        category?.let { parameter("category", it) }
        imageType?.let { parameter("image_type", it) }
    }.body()

    // ── Mapper ───────────────────────────────────────────────────────────────────

    private fun toWallpaper(hit: PixabayHit) = Wallpaper(
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
