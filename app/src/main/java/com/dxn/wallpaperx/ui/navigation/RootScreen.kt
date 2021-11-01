package com.dxn.wallpaperx.ui.navigation

sealed class RootScreen(val title: String, val route: String) {
    object Home : RootScreen("Home", "home_route")
    object Search : RootScreen("Search", "search_route")
    object SetWallpaper : RootScreen("SetWallpaper", "set_wallpaper_route")
}
