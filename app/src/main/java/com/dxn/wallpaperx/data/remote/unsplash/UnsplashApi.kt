package com.dxn.wallpaperx.data.remote.unsplash

import com.dxn.wallpaperx.data.remote.unsplash.models.ImageDto
import com.dxn.wallpaperx.data.remote.unsplash.models.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UnsplashApi {

    @GET("/search/photos")
    suspend fun getImages(
        @Query("query") query : String,
        @Query("page") page :Int = 1,
        @Query("orientation") orientation : String = "portrait",
        @Query("per_page") perPage: Int = 20,
        @Query("client_id") apikey: String
    ) : Response

    @GET("/photos/{id}")
    suspend fun getImage(

    ) : ImageDto

}