package com.dxn.wallpaperx.app.ui.screen.setWallpaper

import com.dxn.wallpaperx.data.model.Wallpaper

data class SetWallpaperUiState(
    val wallpaper: Wallpaper? = null,
) {
    companion object {
        val INITIAL = SetWallpaperUiState(null)
    }
}
