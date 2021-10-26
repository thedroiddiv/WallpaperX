package com.dxn.wallpaperx.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.dxn.wallpaperx.domain.models.Wallpaper

@ExperimentalCoilApi
@Composable
fun WallpaperCard(
    modifier: Modifier = Modifier,
    wallpaper: Wallpaper,
    elevation: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(8.dp),
    content: @Composable () -> Unit
) {
    Surface(modifier = modifier, elevation = elevation, shape = shape) {
        Box(Modifier.fillMaxSize()) {
            val loader = rememberImagePainter(wallpaper.previewUrl)
            val painter = rememberImagePainter(wallpaper.smallUrl)

            if (painter.state.javaClass == ImagePainter.State.Loading::class.java) {

            }
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = "wallpaper",
                contentScale = ContentScale.Crop
            )
            content()
        }
    }
}
