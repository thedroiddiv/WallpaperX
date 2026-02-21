package com.thedroiddiv.wallpaperx.data.model

data class WallpaperCollection(
    val id: String,
    val title: String,
    val totalPhotos: Int,
    val coverPhoto: String,
    val tags: List<String>,
)
