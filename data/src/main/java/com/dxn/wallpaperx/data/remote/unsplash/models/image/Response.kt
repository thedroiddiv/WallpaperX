package com.dxn.wallpaperx.data.remote.unsplash.models.image

import com.google.gson.annotations.SerializedName

data class Response(
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<ImageDto>
)
