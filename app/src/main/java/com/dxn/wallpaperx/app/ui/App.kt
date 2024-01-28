package com.dxn.wallpaperx.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.dxn.wallpaperx.app.ui.components.BottomNavBar
import com.dxn.wallpaperx.app.ui.components.WallpaperAppBar
import com.dxn.wallpaperx.app.ui.nav.Screen
import com.dxn.wallpaperx.app.ui.screen.home.HomeScreenVM
import com.dxn.wallpaperx.app.ui.screen.home.WallpapersScreen
import com.dxn.wallpaperx.app.ui.screen.search.SearchScreen
import com.dxn.wallpaperx.app.ui.screen.search.SearchScreenVM
import com.dxn.wallpaperx.app.ui.screen.setWallpaper.SetWallpaperScreen
import com.dxn.wallpaperx.app.ui.screen.setWallpaper.SetWallpaperScreenVM
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val screen =
        remember(navBackStackEntry) {
            Screen.entries.firstOrNull { it.route == navBackStackEntry?.destination?.route }
        }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val homeScreenVM: HomeScreenVM = koinViewModel()
    val homeUiState by homeScreenVM.uiState.collectAsState()
    val wallpapers = homeScreenVM.wallpapers.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            screen?.let {
                WallpaperAppBar(
                    destination = it,
                    navigateUp = { navController.navigateUp() },
                    toSettings = { navController.navigate(Screen.Settings.route) },
                    toSearch = { navController.navigate(Screen.Search.route) },
                    scrollBehavior = scrollBehavior,
                )
            }
        },
        bottomBar = {
            if (screen == Screen.Wallpapers || screen == Screen.Collections || screen == Screen.Favourites) {
                BottomNavBar(
                    currentScreen = screen,
                    screens = Screen.homeScreens,
                    onClick = {
                        navController.navigate(it.route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
        ) {
            NavHost(navController = navController, startDestination = "home_screens") {
                navigation(
                    startDestination = Screen.Wallpapers.route,
                    route = "home_screens",
                ) {
                    composable(Screen.Wallpapers.route) {
                        WallpapersScreen(
                            uiState = homeUiState,
                            wallpapers = wallpapers,
                            onFavClick = homeScreenVM::addToFavourites,
                            onWallpaperClick = { navController.navigate("${Screen.SetWallpaper.route}/${it.id}") },
                        )
                    }
                    composable(Screen.Collections.route) {
                        LazyColumn(Modifier.fillMaxWidth()) {
                            items(50) {
                                Text(text = "Item $it")
                            }
                        }
                    }

                    composable(Screen.Favourites.route) {
                        LazyColumn(Modifier.fillMaxWidth()) {
                            items(50) {
                                Text(text = "Item $it")
                            }
                        }
                    }
                }

                composable(
                    route = Screen.Search.route,
                ) {
                    val searchScreenVM: SearchScreenVM = koinViewModel()
                    val uiState by searchScreenVM.uiState.collectAsState()
                    val searchResults = searchScreenVM.searchResults.collectAsLazyPagingItems()
                    SearchScreen(
                        uiState = uiState,
                        onQueryChange = searchScreenVM::onQueryChange,
                        onSearchActiveChange = searchScreenVM::onSearchActiveChange,
                        onSearch = searchScreenVM::searchWallpapers,
                        searchResults = searchResults,
                        onFavClick = {},
                        onWallpaperClick = {},
                        navigateUp = navController::navigateUp,
                    )
                }

                composable(
                    route = "${Screen.SetWallpaper.route}/{wallpaperId}",
                    arguments = listOf(navArgument("wallpaperId") { type = NavType.StringType }),
                ) {
                    val viewModel: SetWallpaperScreenVM = koinViewModel()
                    val uiState by viewModel.uiState.collectAsState()
                    SetWallpaperScreen(
                        uiState = uiState,
                        onDownloadClicked = viewModel::download,
                        onLockClicked = viewModel::setLockScreenWallpaper,
                        onHomeClicked = viewModel::setHomeScreenWallpaper,
                        onFavouriteClicked = viewModel::addToFavourites,
                        onShareClicked = viewModel::share,
                        navigateUp = navController::navigateUp,
                    )
                }

                composable(
                    route = Screen.Gallery.route,
                ) {
                    LazyColumn(Modifier.fillMaxWidth()) {
                        items(50) {
                            Text(text = "Item $it")
                        }
                    }
                }

                composable(
                    route = Screen.Settings.route,
                ) {
                    LazyColumn(Modifier.fillMaxWidth()) {
                        items(50) {
                            Text(text = "Item $it")
                        }
                    }
                }
            }
        }
    }
}
