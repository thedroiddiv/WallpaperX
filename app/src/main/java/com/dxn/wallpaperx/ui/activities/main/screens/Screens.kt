package com.dxn.wallpaperx.ui.activities.main.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.dxn.wallpaperx.R

sealed class Screen(
    val title: String,
    val route: String,
    val routeRes: Int,
    val icon: ImageVector
) {
    object Wallpapers : Screen(
        "WallpapersX",
        "route_wallpapers",
        R.string.route_wallpapers,
        Icons.Rounded.Wallpaper
    )

    object Favourites : Screen(
        "Favourites",
        "route_favourites",
        R.string.route_favourites,
        Icons.Rounded.Favorite
    )

    object Downloads : Screen(
        "Downloads",
        "route_downloads",
        R.string.route_downloads,
        Icons.Rounded.Download
    )

    object Settings : Screen(
        "Settings",
        "route_settings",
        R.string.route_settings,
        Icons.Rounded.Settings
    )
}