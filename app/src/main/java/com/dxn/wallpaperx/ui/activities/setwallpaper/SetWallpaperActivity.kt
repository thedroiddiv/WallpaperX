package com.dxn.wallpaperx.ui.activities.setwallpaper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.theme.WallpaperXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetWallpaperActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras
        val wallpaper = extras!!.getSerializable("wallpaper") as Wallpaper

        setContent {
            WallpaperXTheme {

                val viewModel: SetWallpaperViewModel = hiltViewModel()
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = rememberImagePainter(
                            data = wallpaper.wallpaperUrl,
                        ),
                        contentDescription = "wallpaper",
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .align(Alignment.BottomCenter)
                            .background(Color.Red.copy(0.4f))
                    ) {
                        IconButton(onClick = {
                            viewModel.saveWallpaper(wallpaper)
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Download,
                                contentDescription = "download"
                            )
                        }
                    }
                }
            }
        }
    }
}
