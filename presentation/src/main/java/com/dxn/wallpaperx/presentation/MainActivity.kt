package com.dxn.wallpaperx.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dxn.wallpaperx.presentation.ui.App
import com.dxn.wallpaperx.presentation.ui.theme.WallpaperXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { WallpaperXTheme { App() } }
    }
}
