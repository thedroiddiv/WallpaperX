package com.thedroiddiv.wallpaperx.data.remote.pixabay

import com.thedroiddiv.wallpaperx.data.remote.pixabay.dto.PixabaySearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class PixabayApi(private val client: HttpClient) {

    /**
     * Searches Pixabay images. Mirrors [PixabayApi.searchImage] from :data.
     * The API key is already injected by the client's defaultRequest config.
     */
    suspend fun searchImages(
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
}
