package com.dxn.wallpaperx.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.dxn.wallpaperx.data.model.Wallpaper

@ExperimentalCoilApi
@Composable
fun WallpaperCard(
    modifier: Modifier = Modifier,
    wallpaper: Wallpaper,
    isFavourite: Boolean,
    onLikedClicked: () -> Unit,
    onClick: () -> Unit,
    shape: Shape = MaterialTheme.shapes.large
) {
    Surface(modifier = modifier, shape = shape) {
        Box(Modifier.fillMaxSize()) {
            val loader = rememberAsyncImagePainter(wallpaper.previewUrl)
            val painter = rememberAsyncImagePainter(wallpaper.smallUrl)

            if (painter.state.javaClass == AsyncImagePainter.State.Loading::class.java) {
                Image(
                    modifier = Modifier.matchParentSize(),
                    painter = loader,
                    contentDescription = "wallpaper",
                    contentScale = ContentScale.Crop
                )
            }
            Image(
                modifier = Modifier.matchParentSize(),
                painter = painter,
                contentDescription = "wallpaper",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {
                        onClick()
                    }
            ) {
                FavouriteButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp),
                    isFavourite = isFavourite
                ) {
                    onLikedClicked()
                }
            }
        }
    }
}
