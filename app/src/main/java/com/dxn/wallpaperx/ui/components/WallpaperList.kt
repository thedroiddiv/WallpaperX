package com.dxn.wallpaperx.ui.components

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.ui.SetWallpaperActivity
import com.dxn.wallpaperx.ui.activities.main.MainActivity
import kotlinx.coroutines.flow.Flow

@ExperimentalFoundationApi
@Composable
fun WallpaperList(
    lazyListState: LazyListState = rememberLazyListState(),
    dataFlow: Flow<PagingData<Wallpaper>>
) {
    val wallpapers = (dataFlow.collectAsLazyPagingItems())
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        if (wallpapers.itemCount == 0) {
            Text(text = "Uhh no! No items were found")
        }
        LazyVerticalGrid(
            state = lazyListState,
            cells = GridCells.Fixed(2),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            items(wallpapers.itemCount) { index ->
                wallpapers[index]?.let {
                    WallpaperCard(
                        modifier = Modifier
                            .padding(top = if (index == 0 || index == 1) 8.dp else 0.dp)
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(246.dp),
                        wallpaper = it
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                val intent = Intent(context, SetWallpaperActivity::class.java)
                                intent.putExtra("wallpaper", wallpapers[index])
                                context.startActivity(intent)
                            }) {
                            IconButton(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(12.dp),
                                onClick = {

                                }) {
                                Icon(
                                    imageVector = Icons.Rounded.Favorite,
                                    contentDescription = "favourite button",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
