package com.dxn.wallpaperx.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(

    primary = Gray900,
    primaryVariant = Gray900,
    onPrimary = Color.White,

    surface = Gray900,
    onSurface = Color.White,

    onSecondary = Red900 // accent color
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Gray600,
    onPrimary = Color.Black,

    surface = Color.White,
    onSurface = Color.Black,

    onSecondary = Red900 // accent color
)

@Composable
fun WallpaperXTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
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