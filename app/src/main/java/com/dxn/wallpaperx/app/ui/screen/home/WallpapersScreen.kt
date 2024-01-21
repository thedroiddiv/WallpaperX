package com.dxn.wallpaperx.app.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.dxn.wallpaperx.app.ui.components.WallpaperCard
import com.dxn.wallpaperx.data.model.Wallpaper

@Composable
fun WallpapersScreen(
    uiState: HomeUiState,
    wallpapers: LazyPagingItems<Wallpaper>, // TODO: Can we do plain List?
    onFavClick: (Wallpaper) -> Unit,
    onWallpaperClick: (Wallpaper) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(wallpapers.itemCount) { idx ->
            wallpapers[idx]?.let { wallpaper ->
                val isFavourite = uiState.favourites.any { it.id == wallpaper.id }
                WallpaperCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    previewUrl = wallpaper.previewUrl,
                    isFav = isFavourite,
                    onFavClick = { onFavClick(wallpaper) },
                    onClick = { onWallpaperClick(wallpaper) }
                )
            }
        }
    }
}
