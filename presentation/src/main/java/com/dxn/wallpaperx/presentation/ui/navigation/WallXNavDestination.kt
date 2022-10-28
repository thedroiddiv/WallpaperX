package com.dxn.wallpaperx.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

sealed class WallXNavDestination(val title: String, val icon: ImageVector, val route: String) {

    /**
     * Bottom navigation will be shown to these nav destinations only
     */
    sealed class BottomNavDestination(title: String, icon: ImageVector, route: String) :
        WallXNavDestination(title, icon, route) {
        object Home : BottomNavDestination("Home", Icons.Default.Home, "route_home")
        object Collection :
            BottomNavDestination("Collection", Icons.Default.Wallpaper, "route_collection")

        object Downloads : BottomNavDestination("Downloads", Icons.Default.Download, "route_downloads")
        object Settings : BottomNavDestination("Settings", Icons.Default.Settings, "route_settings")

        companion object {
            const val path = "bottom_nav_navigation"
        }
    }

    /**
     * More top level navigation destinations
     */
    object SetWallpaper : BottomNavDestination("Settings", Icons.Default.Home, "route_set_wallpaper")
    object Search : BottomNavDestination("Settings", Icons.Default.Home, "route_ssearch")
}

val BOTTOM_NAV_DESTINATIONS = listOf(
    WallXNavDestination.BottomNavDestination.Home,
    WallXNavDestination.BottomNavDestination.Collection,
    WallXNavDestination.BottomNavDestination.Downloads,
    WallXNavDestination.BottomNavDestination.Settings
)

class NavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: WallXNavDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}
