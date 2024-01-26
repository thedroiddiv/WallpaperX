package com.dxn.wallpaperx.app.ui.screen.setWallpaper

import com.dxn.wallpaperx.data.model.Wallpaper

data class SetWallpaperUiState(
    val wallpaper: Wallpaper? = null,
    val isFavourite: Boolean = false,
    val loading: Boolean = false,
) {
    companion object {
        val INITIAL = SetWallpaperUiState()
    }
}
