package com.dxn.wallpaperx.ui.components

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.domain.models.SavedWallpaper
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.activities.setwallpaper.SetWallpaperActivity
import kotlinx.coroutines.flow.Flow

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun WallpaperList(
    wallpapers: LazyPagingItems<Wallpaper>,
    favouriteIds: List<Int>,
    addFavourite: (Wallpaper) -> Unit,
    removeFavourite: (Int) -> Unit,
    state: LazyListState = rememberLazyListState()
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.padding(bottom = 8.dp),
            state = state
        ) {
            items(wallpapers.itemCount) { index ->
                wallpapers[index]?.let { wallpaper ->
                    val isFavourite = favouriteIds.contains(wallpaper.id)
                    WallpaperCard(
                        modifier = Modifier
                            .padding(top = if (index == 0 || index == 1) 8.dp else 0.dp)
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(246.dp),
                        wallpaper = wallpaper,
                        onClick = {
                            val intent = Intent(context, SetWallpaperActivity::class.java)
                            intent.putExtra("wallpaper", wallpaper)
                            context.startActivity(intent)
                        },
                        isFavourite = isFavourite,
                        onLikedClicked = {
                            if (isFavourite) removeFavourite(wallpaper.id) else addFavourite(
                                wallpaper
                            )
                        }
                    )
                }
            }
            wallpapers.apply {
                when {
                    loadState.append is LoadState.Loading || loadState.refresh is LoadState.Loading -> {
                        item {
                            Box() {
                                CircularProgressIndicator(
                                    modifier = Modifier.padding(16.dp),
                                    color = MaterialTheme.colors.onSurface,
                                    strokeWidth = 2.dp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
