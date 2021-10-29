package com.dxn.wallpaperx.ui.activities.setwallpaper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Wallpaper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.activities.setwallpaper.components.BottomMenu
import com.dxn.wallpaperx.ui.theme.WallpaperXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetWallpaperActivity : ComponentActivity() {
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras
        val wallpaper = extras!!.getSerializable("wallpaper") as Wallpaper

        setContent {
            WallpaperXTheme {

                val viewModel: SetWallpaperViewModel = hiltViewModel()
                val loader = rememberImagePainter(
                    data = wallpaper.previewUrl,
                    builder = {
                        transformations(BlurTransformation(LocalContext.current))
                    }
                )
                val painter = rememberImagePainter(data = wallpaper.wallpaperUrl)
                val isLoading = (painter.state.javaClass == ImagePainter.State.Loading::class.java)

                Box(modifier = Modifier.fillMaxSize()) {
                    if (isLoading) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = loader,
                            contentDescription = "wallpaper",
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(50.dp),
                                color = Color.White,
                                strokeWidth = 4.dp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "just wait a moment...",
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Light,
                                color = Color.White
                            )
                        }

                    } else {

                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painter,
                            contentDescription = "wallpaper",
                            contentScale = ContentScale.Crop
                        )
                        BottomMenu(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            title = wallpaper.user,
                            subtitle = "pixabay.com",
                            onFabClicked = {

                            },
                            onDownload = {

                            },
                            onFavourite = {

                            },
                            onInfo = {

                            }
                        )
                    }
                }
            }
        }
    }
}
