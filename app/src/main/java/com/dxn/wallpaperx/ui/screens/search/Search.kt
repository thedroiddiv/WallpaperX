package com.dxn.wallpaperx.ui.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.ui.components.WallpaperList
import com.dxn.wallpaperx.ui.components.SearchBar

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Search(
    navController: NavHostController
) {
    val viewModel: SearchViewModel = hiltViewModel()
    val dataFlow by remember { viewModel.wallpapers }
    val wallpapers = dataFlow.collectAsLazyPagingItems()
    val favourites by remember { viewModel.favourites }

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                elevation = 0.dp,
                color = MaterialTheme.colors.primary
            ) {
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "back")
                    }
                    SearchBar(
                        modifier = Modifier.weight(1f).fillMaxHeight(0.8f),
                        onSearch = { viewModel.search(it) })
                }
            }
        }
    ) {
        WallpaperList(
            wallpapers = wallpapers,
            favourites = favourites,
            addFavourite = { wallpaper ->
                viewModel.addFavourite(wallpaper)
            },
            removeFavourite = { id ->
                viewModel.removeFavourite(id)
            },
            navController = navController
        )
    }
}


