package com.dxn.wallpaperx.ui.screens.setWallpaper

import android.app.WallpaperManager
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.screens.setWallpaper.components.BottomMenu


private const val TAG = "SetWallpaper"

@ExperimentalCoilApi
@Composable
fun SetWallpaper(
    navController: NavHostController,
    wallpaper: Wallpaper
) {

    val viewModel: SetWallpaperViewModel = hiltViewModel()
    var bottomMenuVisibility by remember { mutableStateOf(true) }
    val favouriteIds by remember { viewModel.favouriteIds }
    val isProgressVisible by remember { viewModel.isProgressVisible }

    val isLiked = (favouriteIds.contains(wallpaper.id))

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
                    modifier = Modifier.size(48.dp),
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
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(key1 = Unit) {
                        detectTapGestures {
                            bottomMenuVisibility = !bottomMenuVisibility
                        }
                    },
                painter = painter,
                contentDescription = "wallpaper",
                contentScale = ContentScale.Crop
            )
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomCenter),
                visible = bottomMenuVisibility,
                enter = slideInVertically(initialOffsetY = { height -> height }),
                exit = slideOutVertically(targetOffsetY = { height -> height })
            ) {
                BottomMenu(
                    modifier = Modifier,
                    title = wallpaper.user,
                    subtitle = "pixabay.com",
                    isLiked = isLiked,
                    userImageUrl = wallpaper.userImageURL,
                    onFabClicked = {
                        viewModel.setWallpaper(
                            wallpaper,
                            WallpaperManager.FLAG_SYSTEM
                        )
                    },
                    onDownload = {
                        viewModel.downloadWallpaper(wallpaper)
                    },
                    onFavourite = {
                        if (isLiked) {
                            viewModel.removeFavourite(wallpaper.id)
                        } else {
                            viewModel.addFavourite(wallpaper)
                        }
                    },
                    onLock = {
                        Log.d(TAG, "onCreate: SetWallpaperUseCases")
                        viewModel.setWallpaper(
                            wallpaper,
                            WallpaperManager.FLAG_LOCK
                        )
                    },
                    isProgressVisible = isProgressVisible
                )
            }
        }
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 16.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(0.0f)),
            onClick = {
                navController.popBackStack()
            }) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "back",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}