package com.dxn.wallpaperx.ui.anim

import androidx.compose.animation.*
import androidx.navigation.NavBackStackEntry
import com.dxn.wallpaperx.ui.navigation.HomeScreen

object FavouritesAnimations {
    @ExperimentalAnimationApi
    fun enterTransition(initial: NavBackStackEntry): EnterTransition {
        return when (initial.destination.route) {
            HomeScreen.Wallpapers.route, HomeScreen.Collections.route -> {
                slideInHorizontally(initialOffsetX = { 1000 })
            }
            HomeScreen.Setting.route -> {
                slideInHorizontally(initialOffsetX = { -1000 })
            }
            else -> {
                slideInHorizontally()
            }
        }
    }

    @ExperimentalAnimationApi
    fun exitTransition(target: NavBackStackEntry): ExitTransition {
        return when (target.destination.route) {
            HomeScreen.Wallpapers.route, HomeScreen.Collections.route -> {
                slideOutHorizontally(targetOffsetX = { 1000 })
            }
            HomeScreen.Setting.route -> {
                slideOutHorizontally(targetOffsetX = { -1000 })
            }
            else -> {
                slideOutHorizontally(targetOffsetX = { -1000 })
            }
        }
    }
}
