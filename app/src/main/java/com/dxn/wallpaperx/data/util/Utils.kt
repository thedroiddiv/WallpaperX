package com.dxn.wallpaperx.data.util

import com.dxn.wallpaperx.data.models.Hit
import com.dxn.wallpaperx.domain.models.Wallpaper

fun hitToWallpaper(hit: Hit): Wallpaper = Wallpaper(
    id = hit.id,
    thumbUrl = hit.webformatURL,
    wallpaperUrl = hit.largeImageURL
)

fun hitsToWallpapers(hits: List<Hit>): List<Wallpaper> =
    hits.map { hit ->
        hitToWallpaper(hit)
    }