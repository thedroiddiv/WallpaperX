package com.dxn.wallpaperx.presentation.ui.screens.home

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.presentation.ui.components.WallXTitleBar
import com.dxn.wallpaperx.presentation.ui.components.WallpaperCard
import com.dxn.wallpaperx.presentation.ui.theme.Padding

@Composable
fun HomeScreen(
    wallpapers: List<Wallpaper>,
    onSearchClicked: () -> Unit,
    onWallpaperClicked: (Wallpaper) -> Unit,
    onDownloadClicked: (Wallpaper) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            WallXTitleBar(title = "Wallpapers")
        }
        items(20) { count ->
            WallpaperCard(
                modifier = Modifier
                    .padding(Padding.gridItemPadding(count))
                    .height(240.dp),
                previewUrl = "https://picsum.photos/200/300",
                wallpaperUrl = "https://picsum.photos/1345/1920",
                onDownloadClicked = { }
            ) {}
        }
    }
}
