package com.dxn.wallpaperx.data

import com.dxn.wallpaperx.data.remote.models.Hit
import com.dxn.wallpaperx.domain.models.Wallpaper

fun hitToWallpaper(hit: Hit): Wallpaper = Wallpaper(
    id = hit.id,
    previewUrl = hit.previewURL,
    smallUrl = hit.webformatURL,
    wallpaperUrl = hit.largeImageURL,
    user = hit.user,
    userImageURL = hit.userImageURL
)

fun hitsToWallpapers(hits: List<Hit>): List<Wallpaper> =
    hits.map { hit ->
        hitToWallpaper(hit)
    }