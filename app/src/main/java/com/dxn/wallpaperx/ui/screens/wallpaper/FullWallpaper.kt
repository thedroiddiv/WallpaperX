package com.dxn.wallpaperx.ui.screens.wallpaper

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter

@Composable
fun FullWallpaper(
    wallpaperUrl: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberImagePainter(wallpaperUrl),
            contentDescription = "wallpaper",
            contentScale = ContentScale.Crop
        )
    }
}