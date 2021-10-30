package com.dxn.wallpaperx.ui.components

import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.Transformation
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.activities.setwallpaper.SetWallpaperActivity

@ExperimentalCoilApi
@Composable
fun WallpaperCard(
    modifier: Modifier = Modifier,
    wallpaper: Wallpaper,
    isFavourite: Boolean,
    onLikedClicked: () -> Unit,
    onClick: () -> Unit,
    elevation: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(8.dp),
) {
    Surface(modifier = modifier, elevation = elevation, shape = shape) {
        Box(Modifier.fillMaxSize()) {
            val loader = rememberImagePainter(
                data = wallpaper.previewUrl,
                builder = {
                    transformations(BlurTransformation(LocalContext.current))
                }
            )
            val painter = rememberImagePainter(wallpaper.smallUrl)

            if (painter.state.javaClass == ImagePainter.State.Loading::class.java) {
                Image(
                    modifier = Modifier.matchParentSize(),
                    painter = loader,
                    contentDescription = "wallpaper",
                    contentScale = ContentScale.Crop
                )
            }
            Image(
                modifier = Modifier.matchParentSize(),
                painter = painter,
                contentDescription = "wallpaper",
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .matchParentSize()
                .clickable {
                    onClick()
                }) {
                FavouriteButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp),
                    isFavourite = isFavourite
                ) {
                    onLikedClicked()
                }
            }
        }
    }
}
