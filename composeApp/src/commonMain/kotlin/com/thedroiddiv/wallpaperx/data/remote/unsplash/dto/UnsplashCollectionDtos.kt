package com.thedroiddiv.wallpaperx.data.remote.unsplash.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Subset of Unsplash collection fields consumed by the mapper. */
@Serializable
data class UnsplashCollectionDto(
    val id: String,
    val title: String,
    @SerialName("total_photos") val totalPhotos: Int,
    @SerialName("cover_photo") val coverPhoto: UnsplashCoverPhoto,
)

@Serializable
data class UnsplashCoverPhoto(
    val urls: UnsplashCoverPhotoUrls,
)

@Serializable
data class UnsplashCoverPhotoUrls(
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
)
