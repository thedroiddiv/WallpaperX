package com.dxn.wallpaperx.ui.screens.home.wallpapers

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.dxn.wallpaperx.ui.components.WallpaperCard
import com.dxn.wallpaperx.ui.screens.home.HomeScreenVM
import com.google.gson.Gson

@Composable
fun WallpapersScreen(
    viewModel: HomeScreenVM
) {
    val wallpapers = viewModel.wallpapers.collectAsLazyPagingItems()

    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 8.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(wallpapers.itemCount) { index ->
            wallpapers[index]?.let { wallpaper ->
                val isFavourite = false
//                val isFavourite = favourites.any { it.id == wallpaper.id }
                WallpaperCard(
                    modifier = Modifier
                        .padding(top = if (index == 0 || index == 1) 8.dp else 0.dp)
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(246.dp),
                    wallpaper = wallpaper,
                    onClick = {
                        val data = Uri.encode(Gson().toJson(wallpaper))
                        // navController.navigate("${RootScreen.SetWallpaper.route}/$data")
                    },
                    isFavourite = isFavourite,
                    onLikedClicked = {
                        if (isFavourite) {
                            // removeFavourite(wallpaper.id)
                        } else {
//                            addFavourite(
//                                wallpaper
//                            )
                        }
                    }
                )
            }
        }
    }
}
