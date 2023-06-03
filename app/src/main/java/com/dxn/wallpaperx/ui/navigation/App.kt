package com.dxn.wallpaperx.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dxn.wallpaperx.ui.components.appbar.HomeAppBar
import com.dxn.wallpaperx.ui.screens.home.HomeScreenVM
import com.dxn.wallpaperx.ui.screens.home.wallpapers.WallpapersScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    val showNavigationBar by remember(navBackStackEntry) {
        derivedStateOf {
            val currentRoute = navBackStackEntry?.destination?.route
            val homeRoutes = BottomNavDestinations.values().map { it.route }
            homeRoutes.contains(currentRoute)
        }
    }

    Scaffold(
        Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AnimatedVisibility(visible = showNavigationBar) {
                val currentRoute = navBackStackEntry?.destination?.route
                val destination =
                    BottomNavDestinations.values().firstOrNull { it.route == currentRoute }
                destination?.let {
                    HomeAppBar(
                        destination = destination,
                        scrollBehavior = scrollBehavior,
                        onSearch = { navController.navigate("search_screen") },
                        onSettings = { navController.navigate("settings_screen") }
                    )
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = showNavigationBar) {
                BottomNavigationBar(
                    navController = navController,
                    navItems = BottomNavDestinations.values()
                )
            }
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
                        val viewModel = it.sharedViewModel<HomeScreenVM>(navController)
                        WallpapersScreen(viewModel = viewModel)
                    }

                    composable(BottomNavDestinations.Collections.route) {
                        val viewModel = it.sharedViewModel<HomeScreenVM>(navController)

                        Text(text = "Collections")
                    }

                    composable(BottomNavDestinations.Favourites.route) {
                        val viewModel = it.sharedViewModel<HomeScreenVM>(navController)

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
