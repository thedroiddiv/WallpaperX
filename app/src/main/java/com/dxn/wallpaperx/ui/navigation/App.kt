package com.dxn.wallpaperx.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()

    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {},
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                navItems = BottomNavDestinations.values()
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            NavHost(navController = navController, startDestination = "home_navigation") {
                navigation(BottomNavDestinations.Wallpapers.route, "home_navigation") {

                    composable(BottomNavDestinations.Wallpapers.route) {
                        Text(text = "Wallpapers")
                    }

                    composable(BottomNavDestinations.Collections.route) {
                        Text(text = "Collections")
                    }

                    composable(BottomNavDestinations.Favourites.route) {
                        Text(text = "Favourites")
                    }
                }

                composable("search_screen") {
                }

                composable(
                    "set_wallpaper_screen?wallpaper_id={wallpaper_id}",
                    listOf(navArgument("wallpaper_id") { nullable = false })
                ) {
                }

                composable("settings_screen") {
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}
