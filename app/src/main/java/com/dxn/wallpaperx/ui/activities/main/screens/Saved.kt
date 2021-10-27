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
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.ui.activities.main.MainActivityViewModel
import com.dxn.wallpaperx.ui.activities.setwallpaper.SetWallpaperActivity
import com.dxn.wallpaperx.ui.components.WallpaperCard

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Saved(
    viewModel: MainActivityViewModel
) {
    viewModel.loadSavedWallpapers()
    val wallpapers by remember { viewModel.savedWallpapers }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        if (wallpapers.isEmpty()) {
            Text(text = "Uhh no! No items were found")
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            items(wallpapers.size) { index ->
                WallpaperCard(
                    modifier = Modifier
                        .padding(top = if (index == 0 || index == 1) 8.dp else 0.dp)
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(246.dp),
                    bitmap = wallpapers[index].bitmap
                ) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            val intent = Intent(context, SetWallpaperActivity::class.java)
                            intent.putExtra("wallpaper", wallpapers[index])
                            context.startActivity(intent)
                        }) {
                        IconButton(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(12.dp),
                            onClick = {

                            }) {
                            Icon(
                                imageVector = Icons.Rounded.Favorite,
                                contentDescription = "favourite button",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
//    WallpaperList(wallpapers = wallpapers)
}