package com.dxn.wallpaperx.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun CategoryCard(
    modifier: Modifier,
    title: String,
    backgroundImage: String,
    onClick: () -> Unit
) {
    Card(
        modifier =
            modifier
                .clickable { onClick() }
                .clip(MaterialTheme.shapes.medium),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberImagePainter(data = backgroundImage),
                contentDescription = "category or collection ",
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(0.4f)),
            )
            Text(
                modifier =
                    Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart),
                text = title,
                color = Color.White,
            )
        }
    }
}
