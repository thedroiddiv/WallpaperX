package com.dxn.wallpaperx.data.remote.pixabay.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hit(
    val id: Long,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val previewWidth: Int,
    val previewHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int,
    val webformatHeight: Int,
    val largeImageURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Long,
    val views: Int,
    val downloads: Int,
    val collections: Int,
    val likes: Int,
    val comments: Int,
    @SerialName("user_id") val userId: Long,
    val user: String,
    val userImageURL: String,
    val noAiTraining: Boolean,
    val isAiGenerated: Boolean,
    val isGRated: Boolean,
    val isLowQuality: Boolean,
    val userURL: String
)
