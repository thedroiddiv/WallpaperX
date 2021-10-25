package com.dxn.wallpaperx.ui.screens.wallpapers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun Wallpapers(
    viewModel : WallpapersViewModel = hiltViewModel()
) {
    val wallpapers = viewModel.wallpapers.collectAsLazyPagingItems()
    Column(Modifier.fillMaxSize()) {
        LazyColumn(){
           items(wallpapers) { wallpaper ->
               Text(text = wallpaper?.wallpaperUrl?:"undefined")
           }
        }
    }
}