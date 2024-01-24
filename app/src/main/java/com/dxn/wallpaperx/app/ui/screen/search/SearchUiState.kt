package com.dxn.wallpaperx.app.ui.screen.search

import com.dxn.wallpaperx.data.model.Wallpaper

data class SearchUiState(
    val isActive: Boolean = false,
    val query: String = "",
    val favourites: List<Wallpaper> = listOf(),
)
