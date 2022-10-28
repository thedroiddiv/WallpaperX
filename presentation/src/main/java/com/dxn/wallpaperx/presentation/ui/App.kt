package com.dxn.wallpaperx.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()
    val navigationActions =
        remember(navController) { NavigationActions(navController) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route
            ?: WallXNavDestination.BottomNavDestination.Home.route

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = BOTTOM_NAV_DESTINATIONS.any { it.route == selectedDestination },
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                BottomNavigation(
                    selectedDestination = selectedDestination,
                    navigateToTopLevelDestination = navigationActions::navigateTo
                )
            }
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = BOTTOM_NAV_DESTINATIONS.any { it.route == selectedDestination },
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(onClick = {
                    navController.navigate(WallXNavDestination.Search.route)
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        },
        content = {
            NavigationGraph(
                modifier = Modifier
                    .padding(it),
                navController = navController
            )
        }
    )
}
