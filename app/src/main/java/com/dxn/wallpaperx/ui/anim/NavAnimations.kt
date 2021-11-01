package com.dxn.wallpaperx.ui.anim

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry
import com.dxn.wallpaperx.ui.navigation.HomeScreen

fun slideInFromRight(initial: NavBackStackEntry, target: NavBackStackEntry): EnterTransition? {
    return when (initial.destination.route) {
        HomeScreen.Wallpapers.route, HomeScreen.Favourites.route, HomeScreen.Setting.route ->
            slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300))
        else -> null
    }
}

fun slideInFromLeft(initial: NavBackStackEntry, target: NavBackStackEntry): EnterTransition? {
    return when (initial.destination.route) {
        HomeScreen.Wallpapers.route, HomeScreen.Favourites.route, HomeScreen.Setting.route ->
            slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300))
        else -> null
    }
}


fun slideOutToLeft(initial: NavBackStackEntry, target: NavBackStackEntry): ExitTransition? {
    return when (target.destination.route) {
        HomeScreen.Wallpapers.route, HomeScreen.Favourites.route, HomeScreen.Setting.route ->
            slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))
        else -> null
    }
}

fun slideOutToRight(initial: NavBackStackEntry, target: NavBackStackEntry): ExitTransition? {
    return when (target.destination.route) {
        HomeScreen.Wallpapers.route, HomeScreen.Favourites.route, HomeScreen.Setting.route ->
            slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300))
        else -> null
    }
}