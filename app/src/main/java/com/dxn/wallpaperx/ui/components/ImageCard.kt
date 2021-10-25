package com.dxn.wallpaperx.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.dxn.wallpaperx.domain.models.Wallpaper

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
            Image(
                modifier=Modifier.fillMaxSize(),
                painter = rememberImagePainter(
                    data = wallpaper.thumbUrl,
                ),
                contentDescription = "wallpaper",
                contentScale = ContentScale.Crop
            )
            content()
        }
    }
}
