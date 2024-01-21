package com.dxn.wallpaperx.app.ui.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dxn.wallpaperx.app.R

enum class Screen(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
) {
    /** Home Screens */
    Wallpapers("wallpapers_screen", R.string.wallpaper, R.drawable.ic_wallpaper_24),
    Collections("collections_screen", R.string.collections, R.drawable.ic_explore_24),
    Favourites("favourites_screen", R.string.favourites, R.drawable.ic_favorite_24),

    Search("search_wallpaper_screen", R.string.search_wallpaper, R.drawable.ic_search_24),
    SetWallpaper(
        "set_wallpaper_screen",
        R.string.set_wallpaper,
        R.drawable.ic_add_photo_alternate_24,
    ),
    Gallery("gallery_wallpaper_screen", R.string.set_wallpaper, R.drawable.ic_wallpaper_24),
    Settings("settings_screen", R.string.settings, R.drawable.ic_settings_24),
    ;

    companion object {
        val homeScreens = listOf(Wallpapers, Collections, Favourites)
    }
}
