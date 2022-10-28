package com.dxn.wallpaperx.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun ImageCard(
    modifier: Modifier,
    imageUrl: String,
    previewUrl: String
) {
    val loader = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = previewUrl).transformations()
            .build()
    )
    AsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = null,
        placeholder = loader,
        contentScale = ContentScale.Crop
    )
}
