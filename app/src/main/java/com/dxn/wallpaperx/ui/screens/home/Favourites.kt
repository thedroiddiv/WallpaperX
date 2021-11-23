package com.dxn.wallpaperx.ui.screens.home

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.MainViewModel
import com.dxn.wallpaperx.ui.components.WallpaperCard
import com.dxn.wallpaperx.ui.navigation.RootScreen
import com.google.gson.Gson


@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Favourites(
    viewModel: MainViewModel,
    favourites: List<Wallpaper>,
    listState: LazyListState,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        if (favourites.isEmpty()) {
            Text(text = "Uhh no! No items were found")
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            state = listState,
        ) {
            itemsIndexed(favourites) { index, wallpaper ->
                WallpaperCard(
                    modifier = Modifier
                        .padding(top = if (index == 0 || index == 1) 8.dp else 0.dp)
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(246.dp),
                    wallpaper = wallpaper,
                    isFavourite = true,
                    onLikedClicked = {
                        viewModel.removeFavourite(wallpaper.id)
                    },
                    onClick = {
                        val data = Uri.encode(Gson().toJson(wallpaper))
                        navController.navigate("${RootScreen.SetWallpaper.route}/$data")
                    }
                )
            }
        }
    }
}