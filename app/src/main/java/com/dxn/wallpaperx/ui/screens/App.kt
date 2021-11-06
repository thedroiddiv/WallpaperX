package com.dxn.wallpaperx.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.MainViewModel
import com.dxn.wallpaperx.ui.anim.CollectionsAnimations
import com.dxn.wallpaperx.ui.anim.FavouritesAnimations
import com.dxn.wallpaperx.ui.anim.SetWallpaperAnimations
import com.dxn.wallpaperx.ui.anim.WallpapersAnimations
import com.dxn.wallpaperx.ui.navigation.HomeScreen
import com.dxn.wallpaperx.ui.navigation.RootScreen
import com.dxn.wallpaperx.ui.components.BottomBar
import com.dxn.wallpaperx.ui.components.TopBar
import com.dxn.wallpaperx.ui.screens.setWallpaper.Gallery
import com.dxn.wallpaperx.ui.screens.home.collections.Collections
import com.dxn.wallpaperx.ui.screens.home.favourites.Favourites
import com.dxn.wallpaperx.ui.screens.home.settings.Settings
import com.dxn.wallpaperx.ui.screens.home.wallpapers.Wallpapers
import com.dxn.wallpaperx.ui.screens.search.Search
import com.dxn.wallpaperx.ui.screens.setWallpaper.SetWallpaper
import com.dxn.wallpaperx.ui.screens.splash.SplashScreen
import com.dxn.wallpaperx.ui.screens.wallpaperList.CollectionWallpapers
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
    val backgroundColor = MaterialTheme.colors.background
    SideEffect {
        systemUiController.setNavigationBarColor(backgroundColor)
        systemUiController.setStatusBarColor(backgroundColor)
    }
    val mainViewModel: MainViewModel = hiltViewModel()
    val wallpapers = mainViewModel.wallpapers.collectAsLazyPagingItems()
    val collections = mainViewModel.collections.collectAsLazyPagingItems()
    val favourites by remember { mainViewModel.favourites }
    val wallpaperListState = rememberLazyListState()
    val favouriteListState = rememberLazyListState()
    val collectionListState = rememberLazyListState()
    val screens = listOf(
        HomeScreen.Wallpapers,
        HomeScreen.Collections,
        HomeScreen.Favourites,
        HomeScreen.Setting
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDest = screens.find { it.route == currentRoute }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            val data = Uri.encode(uri.toString())
            navController.navigate(RootScreen.Gallery.route.plus("/${data}"))
        }

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
        },
        floatingActionButton = {
            AnimatedVisibility(visible = (screens.map { it.route }).contains(currentRoute)) {
                FloatingActionButton(
                    onClick = {
                        launcher.launch("image/*")
                    },
                    backgroundColor = MaterialTheme.colors.onPrimary,
                    contentColor = MaterialTheme.colors.primary
                ) {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = "add photo")
                }
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
                composable(
                    route = HomeScreen.Wallpapers.route,
                    enterTransition = { initial, _ -> WallpapersAnimations.enterTransition(initial) },
                    exitTransition = { _, target -> WallpapersAnimations.exitTransition(target) }
                ) {
                    Wallpapers(
                        viewModel = mainViewModel,
                        wallpapers = wallpapers,
                        favourites = favourites,
                        listState = wallpaperListState,
                        navController = navController
                    )
                }
                composable(
                    route = HomeScreen.Collections.route,
                    enterTransition = { initial, _ -> CollectionsAnimations.enterTransition(initial) },
                    exitTransition = { _, target -> CollectionsAnimations.exitTransition(target) }
                ) {
                    Collections(
                        collections = collections,
                        listState = collectionListState,
                        navController = navController
                    )
                }
                composable(
                    route = HomeScreen.Favourites.route,
                    enterTransition = { initial, _ -> FavouritesAnimations.enterTransition(initial) },
                    exitTransition = { _, target -> FavouritesAnimations.exitTransition(target) }
                ) {
                    Favourites(
                        viewModel = mainViewModel,
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
                enterTransition = { initial, _ -> FavouritesAnimations.enterTransition(initial) },
                exitTransition = { _, target -> FavouritesAnimations.exitTransition(target) }
            ) {
                Search(
                    navController = navController,
                    favourites = favourites,
                    addFavourite = { mainViewModel.addFavourite(it) },
                    removeFavourite = { mainViewModel.removeFavourite(it) }
                )
            }
            composable(
                route = RootScreen.Gallery.route.plus("/{imageUri}"),
                enterTransition = { initial, _ -> FavouritesAnimations.enterTransition(initial) },
                exitTransition = { _, target -> FavouritesAnimations.exitTransition(target) },
                arguments = listOf(navArgument("imageUri") { type = NavType.StringType })
            ) { backStack ->
                backStack.arguments?.getString("imageUri")?.let { u ->
                    val uri = Uri.parse(u)
                    Gallery(uri, navController)
                }

            }
            composable(
                route = RootScreen.SetWallpaper.route + "/{wallpaper}",
                enterTransition = { initial, _ -> SetWallpaperAnimations.enterTransition(initial) },
                exitTransition = { _, target -> SetWallpaperAnimations.exitTransition(target) },
                arguments = listOf(navArgument("wallpaper") { type = NavType.StringType })
            ) { backStack ->
                backStack.arguments?.getString("wallpaper")?.let { w ->
                    val wallpaper = Gson().fromJson(w, Wallpaper::class.java)
                    SetWallpaper(
                        navController = navController,
                        wallpaper = wallpaper,
                        favourites = favourites,
                        addFavourite = { mainViewModel.addFavourite(it) },
                        removeFavourite = { mainViewModel.removeFavourite(it) }
                    )
                }
            }
            composable(
                route = RootScreen.CollectionWallpaper.route + "/{collectionId}/{title}",
                enterTransition = { initial, _ -> SetWallpaperAnimations.enterTransition(initial) },
                exitTransition = { _, target -> SetWallpaperAnimations.exitTransition(target) },
                arguments = listOf(navArgument("collectionId") { type = NavType.StringType },
                    navArgument("title") { type = NavType.StringType })
            ) { backStack ->
                backStack.arguments?.getString("collectionId")?.let { id ->
                    val title = backStack.arguments?.getString("title")
                    CollectionWallpapers(
                        collectionId = id,
                        collectionName = title ?: "",
                        favourites = favourites,
                        addFavourite = { mainViewModel.addFavourite(it) },
                        removeFavourite = { mainViewModel.removeFavourite(it) },
                        navController = navController
                    )
                }
            }
        }
    }
}