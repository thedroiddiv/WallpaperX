package com.dxn.wallpaperx.ui.screens.setWallpaper

import android.app.WallpaperManager
import android.content.Intent
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.ui.components.BackButton
import com.dxn.wallpaperx.ui.screens.setWallpaper.components.BottomMenu
import kotlinx.coroutines.launch

private const val TAG = "SetWallpaper"

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun SetWallpaper(
    navController: NavHostController,
    wallpaper: Wallpaper,
    favourites: List<Wallpaper>,
    addFavourite: (Wallpaper) -> Unit,
    removeFavourite: (String) -> Unit
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val viewModel: SetWallpaperViewModel = hiltViewModel()
    var bottomMenuVisibility by remember { mutableStateOf(true) }
    val isProgressVisible by remember { viewModel.isProgressVisible }
    val isLiked = favourites.any { it.id == wallpaper.id }

    val loader = rememberImagePainter(
        data = wallpaper.previewUrl,
        builder = {}
    )
    val painter = rememberImagePainter(data = wallpaper.wallpaperUrl)

    Box(modifier = Modifier.fillMaxSize()) {
        if (painter.state is AsyncImagePainter.State.Loading) {
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
        }
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
                subtitle = "unsplash.com",
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
                        removeFavourite(wallpaper.id)
                    } else {
                        addFavourite(wallpaper)
                    }
                },
                onShare = {
                    scope.launch {
                        val uri = viewModel.saveWallpaper(wallpaper)
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_STREAM, uri)
                            type = "image/jpg"
                        }
                        startActivity(
                            context,
                            Intent.createChooser(sendIntent, "Share wallpaper"),
                            null
                        )
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
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 16.dp),
            navController = navController
        )
    }
}
