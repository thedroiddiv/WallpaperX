package com.dxn.wallpaperx.ui.screens.home.wallpapers

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.components.WallpaperList
import com.dxn.wallpaperx.ui.screens.home.HomeViewModel

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Wallpapers(
    viewModel: HomeViewModel,
    wallpapers: LazyPagingItems<Wallpaper>,
    favouriteIds: List<Int>,
    listState: LazyListState,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {

        WallpaperList(
            wallpapers = wallpapers,
            favouriteIds = favouriteIds,
            addFavourite = { viewModel.addFavourite(it) },
            removeFavourite = { viewModel.removeFavourite(it) },
            state = listState,
            navController = navController
        )
    }
}