package com.dxn.wallpaperx.ui.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.ui.MainViewModel
import com.dxn.wallpaperx.ui.anim.FavouritesAnimations
import com.dxn.wallpaperx.ui.anim.SetWallpaperAnimations
import com.dxn.wallpaperx.ui.navigation.HomeScreen
import com.dxn.wallpaperx.ui.navigation.RootScreen
import com.dxn.wallpaperx.ui.screens.home.Collections
import com.dxn.wallpaperx.ui.screens.home.Favourites
import com.dxn.wallpaperx.ui.screens.home.Settings
import com.dxn.wallpaperx.ui.screens.home.Wallpapers
import com.dxn.wallpaperx.ui.screens.search.Search
import com.dxn.wallpaperx.ui.screens.setWallpaper.Gallery
import com.dxn.wallpaperx.ui.screens.setWallpaper.SetWallpaper
import com.dxn.wallpaperx.ui.screens.splash.SplashScreen
import com.dxn.wallpaperx.ui.screens.wallpaperList.CollectionWallpapers
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalPagerApi
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
    val wallpaperListState = rememberLazyGridState()
    val favouriteListState = rememberLazyGridState()
    val collectionListState = rememberLazyListState()
    val pagerState = rememberPagerState()
    val screens = listOf(
        HomeScreen.Wallpapers,
        HomeScreen.Collections,
        HomeScreen.Favourites,
        HomeScreen.Setting
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            val data = Uri.encode(uri.toString())
            navController.navigate(RootScreen.Gallery.route.plus("/${data}"))
        }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AnimatedVisibility(
                visible = currentRoute == RootScreen.Home.route,
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                        painter = painterResource(id = screens[pagerState.currentPage].resId),
                        contentDescription = "collections"
                    )
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier.padding(16.dp),
                    )
                    IconButton(onClick = {
                        navController.navigate(RootScreen.Search.route)
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            AnimatedVisibility(visible = currentRoute == RootScreen.Home.route) {
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
        AnimatedNavHost(navController = navController, startDestination = RootScreen.Home.route) {
            composable(RootScreen.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(route = RootScreen.Home.route) {
                HorizontalPager(count = screens.size, state = pagerState) { page ->
                    when (screens[page]) {
                        HomeScreen.Wallpapers -> {
                            Wallpapers(
                                viewModel = mainViewModel,
                                wallpapers = wallpapers,
                                favourites = favourites,
                                listState = wallpaperListState,
                                navController = navController
                            )
                        }
                        HomeScreen.Collections -> {
                            Collections(
                                collections = collections,
                                listState = collectionListState,
                                navController = navController
                            )
                        }
                        HomeScreen.Favourites -> {
                            Favourites(
                                viewModel = mainViewModel,
                                favourites = favourites,
                                listState = favouriteListState,
                                navController = navController
                            )
                        }
                        HomeScreen.Setting -> {
                            Settings()
                        }
                    }

                }
            }
            composable(
                route = RootScreen.Search.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
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
                enterTransition = { FavouritesAnimations.enterTransition(this.initialState) },
                exitTransition = { FavouritesAnimations.exitTransition(this.targetState) },
                arguments = listOf(navArgument("imageUri") { type = NavType.StringType })
            ) { backStack ->
                backStack.arguments?.getString("imageUri")?.let { u ->
                    val uri = Uri.parse(u)
                    Gallery(uri, navController)
                }

            }
            composable(
                route = RootScreen.SetWallpaper.route + "/{wallpaper}",
                enterTransition = { SetWallpaperAnimations.enterTransition() },
                exitTransition = { SetWallpaperAnimations.exitTransition() },
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
                enterTransition = { expandIn() },
                exitTransition = { shrinkOut() },
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