package com.dxn.wallpaperx.ui.anim

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.ui.Alignment
import androidx.navigation.NavBackStackEntry
import com.dxn.wallpaperx.ui.navigation.HomeScreen

object WallpapersAnimations {
    fun enterTransition(initial: NavBackStackEntry): EnterTransition {
        return when (initial.destination.route) {
            HomeScreen.Favourites.route, HomeScreen.Setting.route, HomeScreen.Collections.route -> {
                slideInHorizontally(initialOffsetX = { -1000 })
            }
            else -> {
                expandIn(expandFrom = Alignment.Center)
            }
        }
    }

    fun exitTransition(target: NavBackStackEntry): ExitTransition {
        return when (target.destination.route) {
            HomeScreen.Favourites.route, HomeScreen.Setting.route, HomeScreen.Collections.route -> {
                slideOutHorizontally(targetOffsetX = { -1000 })
            }
            else -> {
                shrinkOut(shrinkTowards = Alignment.Center, targetSize = { it })
            }
        }
    }
}