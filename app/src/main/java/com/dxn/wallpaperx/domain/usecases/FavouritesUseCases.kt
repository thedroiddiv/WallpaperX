package com.dxn.wallpaperx.domain.usecases

import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.dxn.wallpaperx.helpers.ResourcesProvider
import javax.inject.Inject

class GetFavourites
@Inject
constructor(
    private val repository: WallpaperRepository,
) {
    suspend operator fun invoke(): List<Wallpaper> = repository.getFavourites()
}

class AddFavourite
@Inject
constructor(
    private val repository: WallpaperRepository
) {
    suspend operator fun invoke(wallpaper: Wallpaper)  = repository.addFavourite(wallpaper)
}

class RemoveFavourite
@Inject
constructor(
    private val repository: WallpaperRepository
) {
    suspend operator fun invoke(id: Int) = repository.removeFavourite(id)
}