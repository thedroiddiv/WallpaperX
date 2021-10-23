package com.dxn.wallpaperx.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dxn.wallpaperx.ui.utils.Screen

@Composable
fun App() {

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val screens = listOf(
        Screen.Wallpapers,
        Screen.Favourites,
        Screen.Downloads,
        Screen.Settings,
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = navController.currentBackStackEntry?.id ?: "undefined") },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            ) {
                screens.forEach {
                    BottomNavigationItem(
                        selected = navController.currentBackStackEntry!!.destination.route == it.route,
                        onClick = {
                            navController.navigate(it.route)
                        },
                        icon = {
                            Icon(imageVector = it.icon, contentDescription = it.title)
                        }
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