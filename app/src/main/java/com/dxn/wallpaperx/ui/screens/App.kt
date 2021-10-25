package com.dxn.wallpaperx.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dxn.wallpaperx.ui.screens.wallpaper.FullWallpaper
import com.dxn.wallpaperx.ui.screens.wallpapers.Wallpapers
import com.dxn.wallpaperx.ui.utils.Screen

const val TAG = "AppComposable"

@ExperimentalFoundationApi
@Composable
fun App() {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val screens = listOf(
        Screen.Wallpapers,
        Screen.Favourites,
        Screen.Downloads,
        Screen.Settings,
    )
    val currentDest = screens.find { it.route == currentRoute }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = currentDest?.title + "")
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                screens.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route)
                        },
                        icon = {
                            Icon(imageVector = screen.icon, contentDescription = screen.title)
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onPrimary.copy(0.2f)
                    )
                }
            }
        }
    ) {

        Column(Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = Screen.Wallpapers.route
            ) {
                composable(route = Screen.Wallpapers.route) {
                    Wallpapers(navController)
                }
                composable(
                    route = Screen.FullWallpaper.route.plus("/{wallpaperUrl}"),
                    arguments = listOf(navArgument("wallpaperUrl") { type = NavType.StringType })
                )
                {
                    val wallpaperUrl = it.arguments?.getString("wallpaperUrl")
                    FullWallpaper(wallpaperUrl!!)
                }
                composable(route = Screen.Favourites.route) {

                }
                composable(route = Screen.Downloads.route) {

                }
                composable(route = Screen.Settings.route) {

                }
                composable(route = Screen.Search.route) {

                }
            }
        }
    }
}