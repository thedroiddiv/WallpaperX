package com.dxn.wallpaperx.ui.activities.main.screens

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.activities.main.MainActivityViewModel
import com.dxn.wallpaperx.ui.activities.setwallpaper.SetWallpaperActivity
import com.dxn.wallpaperx.ui.components.WallpaperCard
import com.dxn.wallpaperx.ui.components.WallpaperList

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Wallpapers(
    viewModel: MainActivityViewModel,
    wallpapers: LazyPagingItems<Wallpaper>,
    favouriteIds: List<Int>,
    listState: LazyListState,
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
            state = listState
        )
    }
}
