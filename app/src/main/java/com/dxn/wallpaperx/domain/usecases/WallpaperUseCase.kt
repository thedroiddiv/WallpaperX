package com.dxn.wallpaperx.domain.usecases

data class WallpaperUseCase(
    val getWallpapers: GetWallpapers,
    val getWallpaper: GetWallpaper,
    val downloadWallpaper: DownloadWallpaper,
    val getDownloadedWallpapers: GetDownloadedWallpapers,
    val setWallpaper: SetWallpaper,
)