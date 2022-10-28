package com.dxn.wallpaperx.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dxn.wallpaperx.presentation.ui.theme.Padding
import com.dxn.wallpaperx.presentation.ui.theme.WallpaperXTheme

@Composable
fun CollectionCard(
    modifier: Modifier = Modifier,
    collectionName: String,
    noOfImages: Int,
    tags: List<String>,
    imageUrl: String,
    onCollectionClick: () -> Unit
) {
    OutlinedCard(modifier = modifier.fillMaxWidth()) {
        Column(
            Modifier
                .fillMaxSize()
                .clickable { onCollectionClick() }
        ) {
            ImageCard(
                modifier = Modifier
                    .clip(CardDefaults.shape)
                    .fillMaxHeight(0.75f),
                imageUrl = imageUrl,
                previewUrl = imageUrl
            )
            Column(modifier = Modifier.padding(Padding.small)) {
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    text = collectionName,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    text = "$noOfImages Images",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun CollectionCardPreview() {
    WallpaperXTheme {
        Surface {
            LazyVerticalGrid(
                modifier = Modifier.padding(Padding.small),
                columns = GridCells.Fixed(2)
            ) {
                items(10) {
                    CollectionCard(
                        modifier = Modifier
                            .height(240.dp)
                            .padding(Padding.small),
                        collectionName = "My Collection",
                        noOfImages = 10,
                        tags = listOf(),
                        imageUrl = "https://picsum.photos/1200/1920"
                    ) { }
                }
            }
        }
    }
}
