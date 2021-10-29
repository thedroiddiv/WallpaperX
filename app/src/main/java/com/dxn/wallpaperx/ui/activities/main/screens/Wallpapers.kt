package com.dxn.wallpaperx.ui.activities.main.screens

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.ui.activities.main.MainActivityViewModel
import com.dxn.wallpaperx.ui.activities.setwallpaper.SetWallpaperActivity
import com.dxn.wallpaperx.ui.components.WallpaperCard

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Wallpapers(
    viewModel: MainActivityViewModel
) {
    val context = LocalContext.current
    val dataFlow by remember { viewModel.wallpapers }
    val favourites by remember { viewModel.favourites }
    val favouriteIds = favourites.map { it.id }
    val wallpapers = dataFlow.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        if (wallpapers.itemCount == 0) {
            Text(text = "Uhh no! No items were found")
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            items(wallpapers.itemCount) { index ->
                wallpapers[index]?.let { wallpaper ->
                    WallpaperCard(
                        modifier = Modifier
                            .padding(top = if (index == 0 || index == 1) 8.dp else 0.dp)
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(246.dp),
                        wallpaper = wallpaper
                    ) {
                        val isFavourite = favouriteIds.contains(wallpaper.id)
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                val intent = Intent(context, SetWallpaperActivity::class.java)
                                intent.putExtra("wallpaper", wallpaper)
                                context.startActivity(intent)
                            }) {
                            IconButton(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(12.dp),
                                onClick = {
                                    if (isFavourite) viewModel.removeFavourite(wallpaper.id) else viewModel.addFavourite(
                                        wallpaper
                                    )

                                }) {
                                Icon(
                                    imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "favourite button",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
