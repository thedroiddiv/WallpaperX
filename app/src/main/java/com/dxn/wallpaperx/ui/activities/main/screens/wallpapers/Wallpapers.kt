package com.dxn.wallpaperx.ui.activities.main.screens.wallpapers

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.PagingData
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.components.WallpaperList
import kotlinx.coroutines.flow.Flow

@ExperimentalFoundationApi
@Composable
fun Wallpapers(
    navController: NavController,
    listState: LazyListState,
    viewModel: WallpapersViewModel
) {
    val lazyListState = rememberLazyListState()
    val dataFlow by remember { viewModel.wallpapers }
    WallpaperList(lazyListState = lazyListState, dataFlow = dataFlow)
}
