package com.dxn.wallpaperx.ui.screens.wallpaperList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.ui.components.WallpaperList

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CollectionWallpapers(
    collectionName: String,
    favourites: List<Wallpaper>,
    addFavourite: (Wallpaper) -> Unit,
    removeFavourite: (String) -> Unit,
    navController: NavHostController,
    viewModel: ColWallpapersViewModel = hiltViewModel()
) {
    val wallpapers = viewModel.wallpapers.collectAsLazyPagingItems()
    Column {
        Row(
            modifier = Modifier.height(52.dp).fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "back")
            }
            Text(
                text = collectionName,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
            )
        }
        WallpaperList(
            wallpapers = wallpapers,
            favourites = favourites,
            addFavourite = addFavourite,
            removeFavourite = removeFavourite,
            navController = navController,
        )
    }
}
