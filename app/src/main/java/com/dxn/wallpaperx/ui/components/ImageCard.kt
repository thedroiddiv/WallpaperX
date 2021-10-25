package com.dxn.wallpaperx.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    imageUrl : String,
    elevation : Dp = 4.dp,
    shape : Shape = RoundedCornerShape(8.dp),
    content : @Composable () -> Unit
) {
    Surface(modifier=modifier,  elevation = elevation,shape = shape) {
        Box(Modifier.fillMaxSize()) {

        }
    }
}