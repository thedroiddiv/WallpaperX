package com.dxn.wallpaperx.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dxn.wallpaperx.presentation.ui.theme.Padding
import com.dxn.wallpaperx.presentation.ui.theme.WallpaperXTheme

@Composable
fun WallpaperCard(
    modifier: Modifier = Modifier,
    previewUrl: String,
    wallpaperUrl: String,
    onDownloadClicked: () -> Unit,
    onClick: () -> Unit,
) {
    OutlinedCard(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
        ) {
            ImageCard(
                modifier = Modifier.matchParentSize(),
                imageUrl = wallpaperUrl,
                previewUrl = previewUrl
            )
            Box(
                Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            ),
                        )
                    )
            )
            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = onDownloadClicked,
            ) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun WallpaperCardPreview() {
    WallpaperXTheme {
        val items = Array(10) {
            "https://picsum.photos/200/300" to "https://picsum.photos/1345/1920"
        }
        Surface {
            LazyVerticalGrid(
                modifier = Modifier.padding(Padding.small),
                columns = GridCells.Fixed(2)
            ) {
                items(items) {
                    WallpaperCard(
                        modifier = Modifier
                            .padding(Padding.small)
                            .height(240.dp),
                        previewUrl = it.first,
                        wallpaperUrl = it.second,
                        onDownloadClicked = { }
                    ) {}
                }
            }
        }
    }
}
