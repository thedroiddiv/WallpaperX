package com.dxn.wallpaperx.ui.activities.main

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.ui.activities.main.screens.Favourites
import com.dxn.wallpaperx.ui.activities.main.screens.Saved
import com.dxn.wallpaperx.ui.activities.main.screens.Wallpapers
import com.dxn.wallpaperx.ui.activities.search.SearchActivity

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun MainComposable() {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val screens = listOf(Screen.Wallpapers, Screen.Favourites, Screen.Saved, Screen.Settings)
    val currentDest = screens.find { it.route == currentRoute }
    val context = LocalContext.current as MainActivity
    val viewModel: MainActivityViewModel = hiltViewModel()


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
                    Wallpapers(viewModel)
                }
                composable(route = Screen.Favourites.route) {
                    Favourites(viewModel)
                }
                composable(route = Screen.Saved.route) {
                    Saved(viewModel)
                }
                composable(route = Screen.Settings.route) {

                }
            }
        }
    }
}

