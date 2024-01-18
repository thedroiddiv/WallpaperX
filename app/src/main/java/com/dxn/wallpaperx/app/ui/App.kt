package com.dxn.wallpaperx.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.dxn.wallpaperx.app.ui.components.BottomNavBar
import com.dxn.wallpaperx.app.ui.components.WallpaperAppBar
import com.dxn.wallpaperx.app.ui.nav.Screen


@Composable
fun App() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val screen = remember(navBackStackEntry) {
        Screen.entries.firstOrNull { it.route == navBackStackEntry?.destination?.route }
    }

    Scaffold(
        topBar = {
            screen?.let {
                WallpaperAppBar(
                    destination = it,
                    navigateUp = { navController.navigateUp() },
                    toSettings = { navController.navigate(Screen.Settings.route) },
                    toSearch = { navController.navigate(Screen.Search.route) }
                )
            }
        },
        bottomBar = {
            if (screen == Screen.Wallpapers || screen == Screen.Collections || screen == Screen.Favourites) {
                BottomNavBar(
                    currentScreen = screen,
                    screens = Screen.homeScreens,
                    onClick = { navController.navigate(it.route) }
                )
            }
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(navController = navController, startDestination = "home_screens") {
                navigation(
                    startDestination = Screen.Wallpapers.route,
                    route = "home_screens"
                ) {
                    composable(Screen.Wallpapers.route) {
                        Text(text = stringResource(Screen.Wallpapers.title))
                    }
                    composable(Screen.Collections.route) {
                        Text(text = stringResource(Screen.Collections.title))
                    }

                    composable(Screen.Favourites.route) {
                        Text(text = stringResource(Screen.Favourites.title))
                    }
                }

                composable(
                    route = Screen.Search.route
                ) {
                    Text(text = stringResource(Screen.Search.title))
                }

                composable(
                    route = Screen.SetWallpaper.route,
                ) {
                    Text(text = stringResource(Screen.SetWallpaper.title))
                }

                composable(
                    route = Screen.Gallery.route
                ) {
                    Text(text = stringResource(Screen.Gallery.title))
                }

                composable(
                    route = Screen.Settings.route
                ) {
                    Text(text = stringResource(Screen.Settings.title))
                }
            }
        }
    }
}