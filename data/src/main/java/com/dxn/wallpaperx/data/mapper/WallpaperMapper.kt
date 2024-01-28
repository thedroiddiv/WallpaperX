package com.dxn.wallpaperx.data.mapper

import com.dxn.wallpaperx.data.local.entities.WallpaperEntity
import com.dxn.wallpaperx.data.remote.pixabay.models.Hit

fun Hit.toWallpaper(page: Int): WallpaperEntity {
    return WallpaperEntity(
        id = id.toString(),
        previewUrl = previewURL,
        smallUrl = webformatURL,
        wallpaperUrl = largeImageURL,
        user = user,
        userImageURL = userImageURL,
        page = page,
    )
}
