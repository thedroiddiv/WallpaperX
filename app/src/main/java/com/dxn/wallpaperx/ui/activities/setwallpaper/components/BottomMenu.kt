package com.dxn.wallpaperx.ui.activities.setwallpaper.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun BottomMenu(
    modifier: Modifier,
    title: String,
    userImageUrl : String,
    subtitle: String,
    isLiked: Boolean,
    onFabClicked: () -> Unit,

    onDownload: () -> Unit,
    onLock: () -> Unit,
    onFavourite: () -> Unit,

    isProgressVisible: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Box(Modifier.size(52.dp)) {
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(40.dp)
                                .align(Alignment.Center),
                            painter = rememberImagePainter(data = userImageUrl),
                            contentDescription = "user",
                            contentScale = ContentScale.Crop
                        )
                        if (isProgressVisible) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(52.dp)
                                    .align(Alignment.Center),
                                color = MaterialTheme.colors.onPrimary,
                                strokeWidth = 2.dp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = title, style = MaterialTheme.typography.h6)
                        Text(text = subtitle, style = MaterialTheme.typography.caption)
                    }
                }
                FloatingActionButton(
                    onClick = onFabClicked,
                    backgroundColor = MaterialTheme.colors.onPrimary,
                    contentColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Wallpaper,
                        contentDescription = "set"
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = onDownload) {
                    Icon(
                        imageVector = Icons.Rounded.Download,
                        contentDescription = "download"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Rounded.Info,
                        contentDescription = "info"
                    )
                }
                IconButton(onClick = onFavourite) {
                    Icon(
                        imageVector = if (isLiked) (Icons.Rounded.Favorite) else Icons.Rounded.FavoriteBorder,
                        contentDescription = "favourite"
                    )
                }
                IconButton(onClick =onLock) {
                    Icon(
                        imageVector = Icons.Rounded.LockOpen,
                        contentDescription = "set"
                    )
                }
            }
        }
    }
}