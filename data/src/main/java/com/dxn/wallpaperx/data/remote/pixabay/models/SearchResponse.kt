package com.dxn.wallpaperx.data.remote.pixabay.models

data class SearchResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)
