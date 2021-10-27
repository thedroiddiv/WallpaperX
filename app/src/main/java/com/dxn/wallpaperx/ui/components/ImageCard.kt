package com.dxn.wallpaperx.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.Transformation
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
            val loader = rememberImagePainter(
                data = wallpaper.previewUrl,
                builder = {
                    transformations(BlurTransformation(LocalContext.current))
                }
            )
            val painter = rememberImagePainter(wallpaper.smallUrl)

            if (painter.state.javaClass == ImagePainter.State.Loading::class.java) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = loader,
                    contentDescription = "wallpaper",
                    contentScale = ContentScale.Crop
                )
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

@ExperimentalCoilApi
@Composable
fun WallpaperCard(
    modifier: Modifier = Modifier,
    bitmap: Bitmap,
    elevation: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(8.dp),
    content: @Composable () -> Unit
) {
    Surface(modifier = modifier, elevation = elevation, shape = shape) {
        Box(Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "wallpaper",
                contentScale = ContentScale.Crop
            )
            content()
        }
    }
}