package com.dxn.wallpaperx.domain.usecases

data class WallpaperUseCase(
    val getWallpapers: GetWallpapers,
    val getWallpaper: GetWallpaper,
    val saveWallpaper: SaveWallpaper,
    val getSavedWallpapers: GetSavedWallpapers,
    val setWallpaper: SetWallpaper,
)