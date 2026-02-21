package com.thedroiddiv.wallpaperx

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.thedroiddiv.wallpaperx.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "WallpaperX",
    ) {
        App()
    }
}