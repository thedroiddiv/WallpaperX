package com.dxn.wallpaperx.data.models

data class Response(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)