package com.dxn.wallpaperx.ui.screens.home.wallpapers

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.components.WallpaperList
import com.dxn.wallpaperx.ui.screens.MainViewModel

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Wallpapers(
    viewModel: MainViewModel,
    wallpapers: LazyPagingItems<Wallpaper>,
    favourites: List<Wallpaper>,
    listState: LazyListState,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        WallpaperList(
            wallpapers = wallpapers,
            favourites = favourites,
            addFavourite = { viewModel.addFavourite(it) },
            removeFavourite = { viewModel.removeFavourite(it) },
            state = listState,
            navController = navController
        )
    }
}