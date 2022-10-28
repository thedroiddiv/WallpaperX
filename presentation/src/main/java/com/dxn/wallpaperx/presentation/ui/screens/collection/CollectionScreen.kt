package com.dxn.wallpaperx.presentation.ui.screens.collection

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.presentation.ui.components.CollectionCard
import com.dxn.wallpaperx.presentation.ui.components.WallXTitleBar
import com.dxn.wallpaperx.presentation.ui.theme.Padding

@Composable
fun CollectionScreen(
    collections: List<Collection>,
    onCollectionClicked: (Wallpaper) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            WallXTitleBar(title = "Collections")
        }
        items(10) {
            CollectionCard(
                modifier = Modifier
                    .height(240.dp)
                    .padding(Padding.gridItemPadding(it)),
                collectionName = "My Collection",
                noOfImages = 10,
                tags = listOf(),
                imageUrl = "https://picsum.photos/1200/1920"
            ) { }
        }
    }
}
