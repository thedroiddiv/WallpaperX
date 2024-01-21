package com.dxn.wallpaperx.app.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dxn.wallpaperx.app.ui.components.WallpaperCard
import com.dxn.wallpaperx.data.model.Wallpaper

@Composable
fun FavoritesScreen(
    uiState: HomeUiState,
    onFavClick: (Wallpaper) -> Unit,
    onWallpaperClick: (Wallpaper) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
    ) {
        items(uiState.favourites) { wallpaper ->
            WallpaperCard(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                previewUrl = wallpaper.previewUrl,
                isFav = true,
                onFavClick = { onFavClick(wallpaper) },
                onClick = { onWallpaperClick(wallpaper) },
            )
        }
    }
}
