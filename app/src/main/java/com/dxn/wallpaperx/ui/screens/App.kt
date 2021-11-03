package com.dxn.wallpaperx.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.navigation.HomeScreen
import com.dxn.wallpaperx.ui.navigation.RootScreen
import com.dxn.wallpaperx.ui.screens.home.HomeViewModel
import com.dxn.wallpaperx.ui.screens.home.components.BottomBar
import com.dxn.wallpaperx.ui.screens.home.components.TopBar
import com.dxn.wallpaperx.ui.screens.home.favourites.Favourites
import com.dxn.wallpaperx.ui.screens.home.settings.Settings
import com.dxn.wallpaperx.ui.screens.home.wallpapers.Wallpapers
import com.dxn.wallpaperx.ui.screens.search.Search
import com.dxn.wallpaperx.ui.screens.setWallpaper.SetWallpaper
import com.dxn.wallpaperx.ui.screens.splash.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson


private const val TAG = "AppComposable"

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun App() {

    val navController = rememberAnimatedNavController()
    val systemUiController = rememberSystemUiController()
    val primaryColor = MaterialTheme.colors.primary

    SideEffect {
        systemUiController.setNavigationBarColor(primaryColor)
    }

    val homeViewModel: HomeViewModel = hiltViewModel()
    val wallpapers = homeViewModel.wallpapers.collectAsLazyPagingItems()
    val favourites by remember { homeViewModel.favourites }

    val wallpaperListState = rememberLazyListState()
    val favouriteListState = rememberLazyListState()

    val screens = listOf(HomeScreen.Wallpapers, HomeScreen.Favourites, HomeScreen.Setting)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDest = screens.find { it.route == currentRoute }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AnimatedVisibility(visible = (screens.map { it.route }).contains(currentRoute)) {
                TopBar(currentDest = currentDest, navController = navController)
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = (screens.map { it.route }).contains(currentRoute)) {
                BottomBar(
                    screens = screens,
                    navController = navController,
                    currentRoute = currentRoute
                )
            }
        }
    ) {
        AnimatedNavHost(navController = navController, startDestination = RootScreen.Splash.route) {

            composable(RootScreen.Splash.route) {
                SplashScreen(navController = navController)
            }

            navigation(
                route = RootScreen.Home.route,
                startDestination = HomeScreen.Wallpapers.route
            ) {

                composable(route = HomeScreen.Wallpapers.route) {
                    Wallpapers(
                        viewModel = homeViewModel,
                        wallpapers = wallpapers,
                        favourites = favourites,
                        listState = wallpaperListState,
                        navController = navController
                    )
                }
                composable(route = HomeScreen.Favourites.route) {
                    Favourites(
                        viewModel = homeViewModel,
                        favourites = favourites,
                        listState = favouriteListState,
                        navController = navController
                    )
                }
                composable(route = HomeScreen.Setting.route) {
                    Settings()
                }
            }
            composable(
                route = RootScreen.Search.route,
            ) {
                Search(navController = navController)
            }
            composable(
                route = RootScreen.SetWallpaper.route + "/{wallpaper}",
                arguments = listOf(navArgument("wallpaper") { type = NavType.StringType })
            ) { backStack ->
                backStack.arguments?.getString("wallpaper")?.let {
                    val wallpaper = Gson().fromJson(it, Wallpaper::class.java)
                    SetWallpaper(navController = navController, wallpaper = wallpaper)
                }
            }
        }
    }
}