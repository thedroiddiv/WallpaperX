package com.thedroiddiv.wallpaperx.data.remote.pixabay.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PixabaySearchResponse(
    val hits: List<PixabayHit>,
    val total: Int,
    val totalHits: Int,
)

/**
 * Subset of Pixabay hit fields consumed by the mapper.
 * Pixabay returns camelCase with uppercase URL suffix (previewURL, etc.) so
 * field names match the JSON keys directly — no @SerialName needed except userId.
 */
@Serializable
data class PixabayHit(
    val id: Int,
    val previewURL: String,
    val webformatURL: String,
    val largeImageURL: String,
    val user: String,
    val userImageURL: String,
    @SerialName("user_id") val userId: Int,
)
