package com.dxn.wallpaperx.domain.usecases.favourites

import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.domain.repository.WallpaperRepository
import kotlinx.coroutines.flow.Flow

class GetFavourites
    constructor(
        private val repository: WallpaperRepository,
    ) {
        operator fun invoke(): Flow<List<Wallpaper>> = repository.getFavourites()
    }

class AddFavourite
    constructor(
        private val repository: WallpaperRepository,
    ) {
        suspend operator fun invoke(wallpaper: Wallpaper) = repository.addFavourite(wallpaper)
    }

class RemoveFavourite
    constructor(
        private val repository: WallpaperRepository,
    ) {
        suspend operator fun invoke(id: String) = repository.removeFavourite(id)
    }
