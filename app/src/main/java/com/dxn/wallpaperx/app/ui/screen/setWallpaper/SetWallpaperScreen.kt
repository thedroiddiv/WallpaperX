package com.dxn.wallpaperx.app.ui.screen.setWallpaper

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun SetWallpaperScreen(
    uiState: SetWallpaperUiState,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        uiState.wallpaper?.let {
            val painter = rememberAsyncImagePainter(model = it.wallpaperUrl)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}
