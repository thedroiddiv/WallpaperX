package com.dxn.wallpaperx.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dxn.wallpaperx.R

enum class BottomNavDestinations(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
) {
    Wallpapers("wallpapers_screen", R.string.wallpapers, R.drawable.ic_wallpaper),
    Collections("collections_screen", R.string.collections, R.drawable.ic_collection),
    Favourites("favourites_screen", R.string.favourites, R.drawable.ic_favourite_filled)
}
