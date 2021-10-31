package com.dxn.wallpaperx.ui.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.anim.slideInFromLeft
import com.dxn.wallpaperx.ui.anim.slideInFromRight
import com.dxn.wallpaperx.ui.anim.slideOutToLeft
import com.dxn.wallpaperx.ui.anim.slideOutToRight
import com.dxn.wallpaperx.ui.screens.home.Home
import com.dxn.wallpaperx.ui.screens.search.Search
import com.dxn.wallpaperx.ui.screens.set_wallpaper.SetWallpaper
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.gson.Gson


private const val TAG = "AppComposable"

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun App() {

    val navController = rememberAnimatedNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedNavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(
                route = Screen.Home.route,
                enterTransition = { initial, target -> slideInFromRight(initial, target) },
                exitTransition = { initial, target -> slideOutToLeft(initial, target) },
                popEnterTransition = { initial, target -> slideInFromLeft(initial, target) },
                popExitTransition = {initial, target -> slideOutToRight(initial, target) }
            ) {
                Home(navController = navController)
            }
            composable(
                route = Screen.Search.route,
                enterTransition = { initial, target -> slideInFromRight(initial, target) },
                exitTransition = { initial, target -> slideOutToLeft(initial, target) },
            ) {
                Search(navController = navController)
            }
            composable(
                route = Screen.SetWallpaper.route + "/{wallpaper}",
                enterTransition = { initial, target -> slideInFromRight(initial, target) },
                exitTransition = { initial, target -> slideOutToLeft(initial, target) },
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


sealed class Screen(val title: String, val route: String) {
    object Home : Screen("Home", "home_route")
    object Search : Screen("Search", "search_route")
    object SetWallpaper : Screen("SetWallpaper", "set_wallpaper_route")
}