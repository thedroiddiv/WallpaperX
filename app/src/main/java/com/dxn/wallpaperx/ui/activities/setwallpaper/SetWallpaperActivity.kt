package com.dxn.wallpaperx.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import com.dxn.wallpaperx.ui.theme.WallpaperXTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class SetWallpaperActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras
        val wallpaper = extras!!.getSerializable("wallpaper") as Wallpaper

        setContent {
            WallpaperXTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = rememberImagePainter(
                            data = wallpaper.wallpaperUrl,
                        ),
                        contentDescription = "wallpaper",
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }
    }
}

@HiltViewModel
class SetWallpaperViewModel
@Inject
constructor(
    private val useCase: WallpaperUseCase
) : ViewModel() {

}