package com.dxn.wallpaperx.data

import com.dxn.wallpaperx.data.local.favourites.FavouriteEntity
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

fun favEntityToWallpaper(favouriteEntity: FavouriteEntity): Wallpaper {
    return Wallpaper(
        favouriteEntity.id,
        favouriteEntity.previewUrl,
        favouriteEntity.wallpaperUrl,
        favouriteEntity.smallUrl,
        favouriteEntity.user,
        favouriteEntity.userImageURL
    )
}

fun wallpaperToFavouriteEntity(wallpaper: Wallpaper): FavouriteEntity {
    return FavouriteEntity(
        wallpaper.id,
        wallpaper.previewUrl!!,
        wallpaper.wallpaperUrl!!,
        wallpaper.smallUrl!!,
        wallpaper.user!!,
        wallpaper.userImageURL!!
    )
}

fun hitsToWallpapers(hits: List<Hit>): List<Wallpaper> =
    hits.map { hit ->
        hitToWallpaper(hit)
    }