package com.dxn.wallpaperx.data.remote.unsplash.models.collection

data class Source(
    val ancestry: Ancestry,
    val cover_photo: CoverPhotoX,
    val description: String,
    val meta_description: String,
    val meta_title: String,
    val subtitle: String,
    val title: String
)