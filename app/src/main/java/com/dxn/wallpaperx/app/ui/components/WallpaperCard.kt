package com.dxn.wallpaperx.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dxn.wallpaperx.app.R
import com.dxn.wallpaperx.app.ui.theme.WallpaperXTheme

@Composable
fun WallpaperCard(
    modifier: Modifier = Modifier,
    previewUrl: String,
    isFav: Boolean,
    onFavClick: () -> Unit,
    onClick: () -> Unit
) {

    WallpaperCard(modifier = modifier, isFav = isFav, onFavClick = onFavClick, onClick = onClick) {
        AsyncImage(
            model = previewUrl,
            contentDescription = stringResource(R.string.wallpaper),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WallpaperCard(
    modifier: Modifier = Modifier,
    isFav: Boolean,
    onFavClick: () -> Unit,
    onClick: () -> Unit,
    image: (@Composable BoxScope.() -> Unit)
) {
    OutlinedCard(modifier = Modifier.then(modifier), onClick = onClick) {
        Box(modifier = Modifier.fillMaxSize()) {
            image()
            IconButton(
                onClick = onFavClick,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                val icon = if (isFav) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder
                // TODO: Extract color from image and set tint
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun WallpaperCardPreview() {
    WallpaperXTheme {
        Surface {
            Column(Modifier.padding(24.dp)) {
                WallpaperCard(
                    modifier = Modifier.size(200.dp),
                    isFav = true, onFavClick = { }, onClick = { }
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
