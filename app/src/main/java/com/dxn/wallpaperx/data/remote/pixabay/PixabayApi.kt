package com.dxn.wallpaperx.data.remote.pixabay

import com.dxn.wallpaperx.data.remote.pixabay.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("api")
    suspend fun getWallpapers(
        @Query ("q") query : String,
        @Query("page") page :Int = 1,
        @Query("orientation") orientation : String = "vertical",
        @Query("safesearch") safe: Boolean = false,
        @Query("image_type") imageType : String = "photo",
        @Query("key") apikey:String,
        ) : Response

    @GET("api")
    suspend fun getWallpaper(
        @Query("id") imageId:Int,
        @Query("key") apikey:String
        ) : Response
}