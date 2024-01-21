package com.dxn.wallpaperx.domain.usecases

import com.dxn.wallpaperx.domain.usecases.collections.GetCollections
import com.dxn.wallpaperx.domain.usecases.downloads.DownloadWallpaper
import com.dxn.wallpaperx.domain.usecases.favourites.AddFavourite
import com.dxn.wallpaperx.domain.usecases.favourites.GetFavourites
import com.dxn.wallpaperx.domain.usecases.favourites.RemoveFavourite
import com.dxn.wallpaperx.domain.usecases.wallpapers.GetWallpaper
import com.dxn.wallpaperx.domain.usecases.wallpapers.GetWallpapers
import com.dxn.wallpaperx.domain.usecases.wallpapers.GetWallpapersByCollection
import com.dxn.wallpaperx.domain.usecases.wallpapers.SetWallpaperUseCases
import javax.inject.Inject

data class WallpaperUseCase
    @Inject
    constructor(
        val getWallpapers: GetWallpapers,
        val getWallpaper: GetWallpaper,
        val getCollections: GetCollections,
        val getWallpapersByCollection: GetWallpapersByCollection,
        val downloadWallpaper: DownloadWallpaper,
        val setWallpaperUseCases: SetWallpaperUseCases,
        val getFavourites: GetFavourites,
        val addFavourite: AddFavourite,
        val removeFavourite: RemoveFavourite,
    )
