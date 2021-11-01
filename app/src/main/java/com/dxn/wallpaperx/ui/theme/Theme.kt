package com.dxn.wallpaperx.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(

    primary = Color.Black,
    onPrimary = Color.White,

    surface = Color.Black,
    onSurface = Color.White,

    background = Color.Black,
    onBackground = Color.White,

    secondary = Red900
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    onPrimary = Color.Black,

    surface = Color.White,
    onSurface = Color.Black,

    onSecondary = Red900
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