package com.dxn.wallpaperx.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset.Companion.Infinite
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteButton(
    modifier: Modifier = Modifier,
    isFavourite: Boolean,
    onClick: () -> Unit
) {

    var size by remember { mutableStateOf(if (isFavourite) 32.dp else 24.dp) }
    val animSize by animateDpAsState(targetValue = size)

    IconButton(
        modifier = modifier,
        onClick = {
            onClick()
            size = if (size == 24.dp) 32.dp else 24.dp
        }) {
        Icon(
            imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "favourite button",
            tint = if (isFavourite) Color.Red else Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}