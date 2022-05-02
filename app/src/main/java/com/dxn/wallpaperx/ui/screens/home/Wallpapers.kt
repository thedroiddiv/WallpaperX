package com.dxn.wallpaperx.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.MainViewModel
import com.dxn.wallpaperx.ui.components.WallpaperList

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
    Column(modifier = Modifier.fillMaxSize()) {
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