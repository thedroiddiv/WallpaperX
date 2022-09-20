package com.dxn.wallpaperx.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Black,
    onPrimary = Blue200,
    surface = Color.Black,
    onSurface = Blue200,
    background = Color.Black,
    onBackground = Blue200,
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    onPrimary = Blue500,
    surface = Color.White,
    onSurface = Blue500,
    background = Color.White,
    onBackground = Blue500,
)

@Composable
fun WallpaperXTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
