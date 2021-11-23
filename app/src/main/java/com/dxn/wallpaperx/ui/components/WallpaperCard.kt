package com.dxn.wallpaperx.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.dxn.wallpaperx.domain.models.Wallpaper

@ExperimentalCoilApi
@Composable
fun WallpaperCard(
    modifier: Modifier = Modifier,
    wallpaper: Wallpaper,
    isFavourite: Boolean,
    onLikedClicked: () -> Unit,
    onClick: () -> Unit,
    elevation: Dp = 4.dp,
    shape: Shape = MaterialTheme.shapes.medium,
) {
    Surface(modifier = modifier, elevation = elevation, shape = shape) {
        Box(Modifier.fillMaxSize()) {
            val loader = rememberImagePainter(
                data = wallpaper.previewUrl,
                builder = {
                    transformations(BlurTransformation(LocalContext.current))
                }
            )
            val painter = rememberImagePainter(wallpaper.smallUrl)

            if (painter.state.javaClass == ImagePainter.State.Loading::class.java) {
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
            Box(modifier = Modifier
                .matchParentSize()
                .clickable {
                    onClick()
                }) {
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
