package com.dxn.wallpaperx.domain.usecases

import com.dxn.wallpaperx.domain.repositories.WallpaperRepository

class GetWallpaper(
    private val repository: WallpaperRepository
) {
    suspend operator fun invoke() {

    }
}