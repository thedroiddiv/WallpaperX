package com.dxn.wallpaperx.ui.activities.main

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dxn.wallpaperx.ui.activities.main.screens.Screen
import com.dxn.wallpaperx.ui.activities.main.screens.wallpapers.Wallpapers
import com.dxn.wallpaperx.ui.activities.main.screens.wallpapers.WallpapersViewModel
import com.dxn.wallpaperx.ui.activities.search.SearchActivity

const val TAG = "AppComposable"

@ExperimentalFoundationApi
@Composable
fun MainComposable() {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val screens = listOf(Screen.Wallpapers, Screen.Favourites, Screen.Downloads, Screen.Settings)
    val currentDest = screens.find { it.route == currentRoute }
    val context = LocalContext.current as MainActivity
    val wallpapersViewModel: WallpapersViewModel = hiltViewModel()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = currentDest?.title + "")
                },
                actions = {
                    IconButton(onClick = {
                        context.startActivity(Intent(context, SearchActivity::class.java))
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
                    Wallpapers(navController, rememberLazyListState(), wallpapersViewModel)
                }
                composable(route = Screen.Favourites.route) {

                }
                composable(route = Screen.Downloads.route) {

                }
                composable(route = Screen.Settings.route) {

                }
            }
        }
    }
}

