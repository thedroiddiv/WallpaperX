package com.dxn.wallpaperx.ui.screens.setWallpaper

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dxn.wallpaperx.ui.components.BackButton

@Composable
fun Gallery(
    imageUri: Uri,
    navController: NavHostController
) {

    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val viewModel = hiltViewModel<SetWallpaperViewModel>()
    val isProgressVisible by remember { viewModel.isProgressVisible }

    LaunchedEffect(key1 = true) {
        kotlin.runCatching {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, imageUri)
            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, imageUri)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
        }.getOrElse {
        }
    }
    Box(Modifier.fillMaxSize()) {
        bitmap.value?.let { btm ->
            Image(
                modifier = Modifier.fillMaxSize(),
                bitmap = btm.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .alpha(if (isProgressVisible) 1f else 0f)
                    .background(Color.Black.copy(0.4f))
                    .padding(16.dp)
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator(color = Color.White)
            }
            Row(Modifier.align(Alignment.BottomCenter)) {
                Button(
                    modifier = Modifier
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary
                    ),
                    shape = CircleShape,
                    onClick = {
                        viewModel.setWallpaper(btm, WallpaperManager.FLAG_LOCK)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.LockOpen,
                        contentDescription = "set lock screen wallpaper"
                    )
                }

                Button(
                    modifier = Modifier
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary
                    ),
                    shape = CircleShape,
                    onClick = {
                        viewModel.setWallpaper(btm, WallpaperManager.FLAG_SYSTEM)
                    }
                ) {
                    Text(text = "Set Wallpaper")
                }
            }
            BackButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 16.dp),
                navController = navController
            )
        }
    }
}
