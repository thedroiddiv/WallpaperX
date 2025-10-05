package com.dxn.wallpaperx.data.remote.pixabay.models

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int,
)
