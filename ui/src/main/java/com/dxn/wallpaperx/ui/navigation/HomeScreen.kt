package com.dxn.wallpaperx.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ViewList
import androidx.compose.material.icons.rounded.Wallpaper
import androidx.compose.ui.graphics.vector.ImageVector
import com.dxn.wallpaperx.ui.R


sealed class HomeScreen(val title: String, val route: String, @DrawableRes val resId: Int) {
    object Wallpapers : HomeScreen("Wallpapers", "route_wallpapers", R.drawable.ic_wallpapers)
    object Collections : HomeScreen("Collections", "route_collections", R.drawable.ic_collections)
    object Favourites : HomeScreen("Favourites", "route_favourites", R.drawable.ic_favourites)
    object Setting : HomeScreen("Settings", "route_settings", R.drawable.ic_settings)
}