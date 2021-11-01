package com.dxn.wallpaperx.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Wallpaper
import androidx.compose.ui.graphics.vector.ImageVector


sealed class HomeScreen(val title: String, val route: String, val icon: ImageVector) {
    object Wallpapers : HomeScreen("WallpaperX", "route_wallpapers", Icons.Rounded.Wallpaper)
    object Favourites : HomeScreen("Favourites", "route_favourites", Icons.Rounded.Favorite)
    object Setting : HomeScreen("Settings", "route_settings", Icons.Rounded.Settings)
}