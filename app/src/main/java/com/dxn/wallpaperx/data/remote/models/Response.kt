package com.dxn.wallpaperx.data.remote.models

data class Response(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)