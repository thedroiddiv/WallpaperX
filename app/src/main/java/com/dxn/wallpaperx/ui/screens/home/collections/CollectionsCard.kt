package com.dxn.wallpaperx.ui.screens.home.collections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.ui.theme.WallpaperXTheme

@Composable
fun CollectionCard(
    collection: Collection,
    onClick: () -> Unit
) {
    CollectionCard(
        modifier = Modifier.fillMaxWidth(),
        model = collection.coverPhoto,
        title = collection.title,
        subtitle = collection.totalPhotos.toString(),
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CollectionCard(
    modifier: Modifier = Modifier,
    model: Any?,
    title: String,
    subtitle: String,
    shape: Shape = CardDefaults.outlinedShape,
    onClick: () -> Unit
) {
    val density = LocalDensity.current
    var width by remember { mutableStateOf(0.dp) }
    Card(
        modifier = modifier
            .onSizeChanged {
                width = with(density) { it.width.toDp() }
            },
        shape = shape,
        border = CardDefaults.outlinedCardBorder(),
        onClick = onClick
    ) {
        val painter = rememberAsyncImagePainter(model = model)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .clip(shape)
                .fillMaxWidth()
                .height(width),
            contentScale = ContentScale.Crop
        )
        Column(Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.labelLarge)
            Text(text = subtitle, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
fun CollectionCardPrev() {
    WallpaperXTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            CollectionCard(
                modifier = Modifier
                    .width(256.dp),
                model = R.drawable.ic_launcher_background,
                title = "This is title",
                subtitle = "Subtitle",
                onClick = {}
            )
        }
    }
}
