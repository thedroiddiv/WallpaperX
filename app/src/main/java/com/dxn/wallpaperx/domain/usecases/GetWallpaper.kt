package com.dxn.wallpaperx.domain.usecases

import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.di.ResourcesProvider
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository

class GetWallpaper(
    private val repository: WallpaperRepository,
    private val resourcesProvider: ResourcesProvider

) {
    suspend operator fun invoke(id: Int) =
        repository.getWallpaper(resourcesProvider.getString(R.string.pixabay_api_key), id)
}