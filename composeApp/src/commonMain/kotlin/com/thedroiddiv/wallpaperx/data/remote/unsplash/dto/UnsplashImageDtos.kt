package com.thedroiddiv.wallpaperx.data.remote.unsplash.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Wrapper returned by /search/photos */
@Serializable
data class UnsplashSearchResponse(
    val total: Int,
    @SerialName("total_pages") val totalPages: Int,
    val results: List<UnsplashImageDto>,
)

/** Subset of Unsplash image fields consumed by the mapper. */
@Serializable
data class UnsplashImageDto(
    val id: String,
    val urls: UnsplashImageUrls,
    val user: UnsplashUser,
)

@Serializable
data class UnsplashImageUrls(
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
)

@Serializable
data class UnsplashUser(
    val name: String,
    @SerialName("profile_image") val profileImage: UnsplashProfileImage,
)

@Serializable
data class UnsplashProfileImage(
    val small: String,
)
