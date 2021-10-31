package com.dxn.wallpaperx.ui.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Wallpaper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.ui.anim.slideInFromLeft
import com.dxn.wallpaperx.ui.anim.slideInFromRight
import com.dxn.wallpaperx.ui.anim.slideOutToLeft
import com.dxn.wallpaperx.ui.anim.slideOutToRight
import com.dxn.wallpaperx.ui.screens.home.favourites.Favourites
import com.dxn.wallpaperx.ui.screens.home.settings.Settings
import com.dxn.wallpaperx.ui.screens.home.wallpapers.Wallpapers
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Home(
    navController: NavHostController
) {

    val screens = listOf(Screen.Wallpapers, Screen.Favourites, Screen.Setting)

    val viewModel: HomeViewModel = hiltViewModel()
    val wallpapers = viewModel.wallpapers.collectAsLazyPagingItems()
    val favourites by remember { viewModel.favourites }
    val favouriteIds = favourites.map { it.id }
    val wallpaperListState = rememberLazyListState()

    val localNavController = rememberAnimatedNavController()
    val navBackStackEntry by localNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDest = screens.find { it.route == currentRoute }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = currentDest?.title + "")
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(com.dxn.wallpaperx.ui.screens.Screen.Search.route)
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
                            localNavController.navigate(screen.route)
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


        AnimatedNavHost(
            navController = localNavController,
            startDestination = Screen.Wallpapers.route
        ) {
            composable(
                route = Screen.Wallpapers.route,
                enterTransition = { initial, target -> slideInFromRight(initial, target) },
                exitTransition = { initial, target -> slideOutToLeft(initial, target) },
                popEnterTransition = { initial, target -> slideInFromLeft(initial, target) },
                popExitTransition = { initial, target -> slideOutToRight(initial, target) }
            ) {
                Wallpapers(viewModel, wallpapers, favouriteIds, wallpaperListState, navController)
            }
            composable(
                route = Screen.Favourites.route,
                enterTransition = { initial, target -> slideInFromRight(initial, target) },
                exitTransition = { initial, target -> slideOutToLeft(initial, target) },
                popEnterTransition = { initial, target -> slideInFromLeft(initial, target) },
                popExitTransition = { initial, target -> slideOutToRight(initial, target) }
            ) {
                Favourites(viewModel, favourites, navController)
            }
            composable(
                route = Screen.Setting.route,
                enterTransition = { initial, target -> slideInFromRight(initial, target) },
                exitTransition = { initial, target -> slideOutToLeft(initial, target) },
                popEnterTransition = { initial, target -> slideInFromLeft(initial, target) },
                popExitTransition = { initial, target -> slideOutToRight(initial, target) }
            ) {
                Settings()
            }
        }
    }

}


sealed class Screen(val title: String, val route: String, val icon: ImageVector) {
    object Wallpapers : Screen("WallpaperX", "route_wallpapers", Icons.Rounded.Wallpaper)
    object Favourites : Screen("WallpaperX", "route_favourites", Icons.Rounded.Favorite)
    object Setting : Screen("WallpaperX", "route_settings", Icons.Rounded.Settings)
}