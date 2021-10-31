package com.dxn.wallpaperx.ui.anim

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

fun slideInFromRight(initial:NavBackStackEntry, target:NavBackStackEntry): EnterTransition? {
    return when (initial.destination.route) {
        "Red" ,"Blue"->
            slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500))
        else -> null
    }
}

fun slideInFromLeft(initial:NavBackStackEntry, target:NavBackStackEntry): EnterTransition? {
    return when (initial.destination.route) {
        "Red" ,"Blue"->
            slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(500))
        else -> null
    }
}


fun slideOutToLeft(initial:NavBackStackEntry, target:NavBackStackEntry): ExitTransition? {
    return when (target.destination.route) {
        "Red" ,"Blue" ->
            slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(500))
        else -> null
    }
}

fun slideOutToRight(initial:NavBackStackEntry, target:NavBackStackEntry): ExitTransition? {
    return when (target.destination.route) {
        "Red" ,"Blue"->
            slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
        else -> null
    }
}