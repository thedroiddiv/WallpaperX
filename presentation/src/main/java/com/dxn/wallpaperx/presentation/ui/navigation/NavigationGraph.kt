package com.dxn.wallpaperx.presentation.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dxn.wallpaperx.presentation.ui.screens.collection.CollectionScreen
import com.dxn.wallpaperx.presentation.ui.screens.home.HomeScreen
import com.dxn.wallpaperx.presentation.ui.screens.search.SearchScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = WallXNavDestination.BottomNavDestination.path,
    // viewModel
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {

        /**
         * Bottom navigation will be shown to these nav destinations only
         */
        navigation(
            WallXNavDestination.BottomNavDestination.Home.route,
            WallXNavDestination.BottomNavDestination.path
        ) {
            composable(WallXNavDestination.BottomNavDestination.Home.route) {
                HomeScreen(
                    wallpapers = listOf(),
                    onSearchClicked = { /*TODO*/ },
                    onWallpaperClicked = { /*TODO*/ },
                    onDownloadClicked = { /*TODO*/ }
                )
            }
            composable(WallXNavDestination.BottomNavDestination.Collection.route) {
                CollectionScreen(collections = listOf(), onCollectionClicked = {})
            }
            composable(WallXNavDestination.BottomNavDestination.Downloads.route) {
                Text(text = "Downloads")
            }
            composable(WallXNavDestination.BottomNavDestination.Settings.route) {
                Text(text = "Settings")
            }
        }

        composable(WallXNavDestination.SetWallpaper.route) {
            navController.popBackStack()
        }

        composable(WallXNavDestination.Search.route) {
            SearchScreen(onSearch = {}) { navController.popBackStack() }
        }
    }
}
