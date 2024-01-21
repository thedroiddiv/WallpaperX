package com.dxn.wallpaperx.data.remote.unsplash

import com.dxn.wallpaperx.data.remote.unsplash.models.collection.CollectionDto
import com.dxn.wallpaperx.data.remote.unsplash.models.image.ImageDto
import com.dxn.wallpaperx.data.remote.unsplash.models.image.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {
    @GET("/search/photos")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("orientation") orientation: String = "portrait",
        @Query("per_page") perPage: Int = 20,
    ): Response

    @GET("/collections")
    suspend fun getCollection(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
    ): List<CollectionDto>

    @GET("/collections/{id}/photos")
    suspend fun getPhotosByCollection(
        @Path("id") collectionId: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
    ): List<ImageDto>

    @GET("/photos/{id}")
    suspend fun getImage(): ImageDto
}
