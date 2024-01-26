package com.dxn.wallpaperx.app.ui.screen.setWallpaper

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.dxn.wallpaperx.app.ui.theme.WallpaperXTheme

@Composable
fun SetWallpaperScreen(
    uiState: SetWallpaperUiState,
    onDownloadClicked: () -> Unit,
    onLockClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onFavouriteClicked: () -> Unit,
    onShareClicked: () -> Unit,
    navigateUp: () -> Unit,
) {
    uiState.wallpaper?.let { wallpaper ->
        var isBottomMenuVisible by remember { mutableStateOf(true) }
        SetWallpaperScreen(
            userImageUrl = wallpaper.userImageURL,
            title = wallpaper.user,
            subtitle = "",
            isFavourite = uiState.isFavourite,
            isProgressVisible = false,
            isBottomMenuVisible = isBottomMenuVisible,
            onDownloadClicked = onDownloadClicked,
            onLockClicked = onLockClicked,
            onHomeClicked = onHomeClicked,
            onFavouriteClicked = onFavouriteClicked,
            onShareClicked = onShareClicked,
            navigateUp = navigateUp,
        ) {
            val painter = rememberAsyncImagePainter(model = wallpaper.wallpaperUrl)
            Image(
                painter = painter,
                contentDescription = null,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .clickable { isBottomMenuVisible = !isBottomMenuVisible },
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
private fun SetWallpaperScreen(
    modifier: Modifier = Modifier,
    userImageUrl: String,
    title: String,
    subtitle: String,
    isFavourite: Boolean,
    isProgressVisible: Boolean,
    isBottomMenuVisible: Boolean,
    onDownloadClicked: () -> Unit,
    onLockClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onFavouriteClicked: () -> Unit,
    onShareClicked: () -> Unit,
    navigateUp: () -> Unit,
    wallpaper: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        wallpaper()
        AnimatedVisibility(
            visible = isBottomMenuVisible,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
            ) {
                Row {
                    Box(modifier = Modifier.size(52.dp)) {
                        AsyncImage(
                            model = userImageUrl,
                            contentDescription = title,
                            contentScale = ContentScale.Crop,
                            modifier =
                                Modifier
                                    .clip(CircleShape)
                                    .size(40.dp)
                                    .align(Alignment.Center),
                        )

                        if (isProgressVisible) {
                            CircularProgressIndicator(
                                modifier =
                                    Modifier
                                        .size(52.dp)
                                        .align(Alignment.Center),
                                strokeWidth = 2.dp,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = title, style = MaterialTheme.typography.labelLarge)
                        Text(text = subtitle, style = MaterialTheme.typography.bodySmall)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    FilledIconButton(onClick = onHomeClicked) {
                        Icon(
                            imageVector = Icons.Rounded.Home,
                            contentDescription = "download",
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    IconButton(onClick = onDownloadClicked) {
                        Icon(
                            imageVector = Icons.Rounded.Download,
                            contentDescription = "download",
                        )
                    }
                    IconButton(onClick = onFavouriteClicked) {
                        Icon(
                            imageVector = if (isFavourite) (Icons.Rounded.Favorite) else Icons.Rounded.FavoriteBorder,
                            contentDescription = "favourite",
                        )
                    }
                    IconButton(onClick = onShareClicked) {
                        Icon(
                            imageVector = Icons.Rounded.Share,
                            contentDescription = "share",
                        )
                    }
                    IconButton(onClick = onLockClicked) {
                        Icon(
                            imageVector = Icons.Rounded.LockOpen,
                            contentDescription = "set",
                        )
                    }
                }
            }
        }

        IconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = navigateUp,
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "back button",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview
@Composable
fun SetWallpaperScreenPrev() {
    WallpaperXTheme(darkTheme = true) {
        SetWallpaperScreen(
            isBottomMenuVisible = true,
            isProgressVisible = false,
            userImageUrl = "",
            title = "Awesome folk",
            subtitle = "pixabay.com",
            isFavourite = true,
            onDownloadClicked = { /*TODO*/ },
            onLockClicked = { /*TODO*/ },
            onHomeClicked = { /*TODO*/ },
            onFavouriteClicked = { /*TODO*/ },
            onShareClicked = { /*TODO*/ },
            navigateUp = {},
        ) {
        }
    }
}
