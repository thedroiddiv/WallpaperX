package com.dxn.wallpaperx.ui.screens.home.wallpapers

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.ui.components.WallpaperCard
import com.dxn.wallpaperx.ui.screens.home.HomeScreenVM
import com.google.gson.Gson

@OptIn(ExperimentalCoilApi::class)
@Composable
fun WallpapersScreen(
    viewModel: HomeScreenVM
) {
    val wallpapers = viewModel.wallpapers.collectAsLazyPagingItems()
    val favourites by viewModel.favourites.collectAsState()
    val density = LocalDensity.current

    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(wallpapers.itemCount) { index ->
            var width by remember { mutableStateOf(0.dp) }
            wallpapers[index]?.let { wallpaper ->
                val isFavourite = favourites.any { it.id == wallpaper.id }
                WallpaperCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged { width = with(density) { it.width.toDp() } }
                        .height(width),
                    wallpaper = wallpaper,
                    onClick = {
                        val data = Uri.encode(Gson().toJson(wallpaper))
                        // navController.navigate("${RootScreen.SetWallpaper.route}/$data")
                    },
                    isFavourite = isFavourite,
                    onLikedClicked = {
                        if (isFavourite) {
                            viewModel.removeFavourite(wallpaper.id)
                        } else {
                            viewModel.addFavourite(wallpaper)
                        }
                    }
                )
            }
        }
    }
}
