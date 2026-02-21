package com.thedroiddiv.wallpaperx.data.remote.unsplash

import com.thedroiddiv.wallpaperx.data.remote.unsplash.dto.UnsplashCollectionDto
import com.thedroiddiv.wallpaperx.data.remote.unsplash.dto.UnsplashImageDto
import com.thedroiddiv.wallpaperx.data.remote.unsplash.dto.UnsplashSearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class UnsplashApi(private val client: HttpClient) {

    /** Mirrors UnsplashApi.getImages() — search photos by query. */
    suspend fun searchImages(
        query: String,
        page: Int = 1,
        perPage: Int = 20,
    ): UnsplashSearchResponse = client.get("/search/photos") {
        parameter("query", query)
        parameter("page", page)
        parameter("orientation", "portrait")
        parameter("per_page", perPage)
    }.body()

    /** Mirrors UnsplashApi.getCollection() — paginated list of collections. */
    suspend fun getCollections(
        page: Int = 1,
        perPage: Int = 20,
    ): List<UnsplashCollectionDto> = client.get("/collections") {
        parameter("page", page)
        parameter("per_page", perPage)
    }.body()

    /** Mirrors UnsplashApi.getPhotosByCollection() — photos inside a collection. */
    suspend fun getPhotosByCollection(
        collectionId: String,
        page: Int = 1,
        perPage: Int = 20,
    ): List<UnsplashImageDto> = client.get("/collections/$collectionId/photos") {
        parameter("page", page)
        parameter("per_page", perPage)
    }.body()

    /**
     * Mirrors UnsplashApi.getImage() — single photo by ID.
     * Note: the original had a missing @Path param; fixed here.
     */
    suspend fun getImage(id: String): UnsplashImageDto =
        client.get("/photos/$id").body()
}
