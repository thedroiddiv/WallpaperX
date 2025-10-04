package com.dxn.wallpaperx.ui.components

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.ui.navigation.RootScreen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson

const val TAG = "WallpaperList"

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun WallpaperList(
    wallpapers: LazyPagingItems<Wallpaper>,
    favourites: List<Wallpaper>,
    addFavourite: (Wallpaper) -> Unit,
    removeFavourite: (String) -> Unit,
    state: LazyGridState = rememberLazyGridState(),
    navController: NavHostController,
) {
    SwipeRefresh(
        modifier =
            Modifier
                .fillMaxSize(),
        state = rememberSwipeRefreshState(isRefreshing = (wallpapers.loadState.refresh is LoadState.Loading)),
        indicator = { swipeState, trigger ->
            SwipeRefreshIndicator(
                state = swipeState,
                refreshTriggerDistance = trigger,
                scale = true,
                backgroundColor = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small,
            )
        },
        onRefresh = {
            wallpapers.refresh()
        },
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier =
                Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxSize(),
            state = state,
        ) {
            items(
                count = wallpapers.itemCount,
//                span = {
//
//                }
            ) { index ->
                wallpapers[index]?.let { wallpaper ->
                    val isFavourite = favourites.any { it.id == wallpaper.id }
                    WallpaperCard(
                        modifier =
                            Modifier
                                .padding(top = if (index == 0 || index == 1) 8.dp else 0.dp)
                                .padding(8.dp)
                                .fillMaxWidth()
                                .height(246.dp),
                        wallpaper = wallpaper,
                        onClick = {
                            val data = Uri.encode(Gson().toJson(wallpaper))
                            navController.navigate("${RootScreen.SetWallpaper.route}/$data")
                        },
                        isFavourite = isFavourite,
                        onLikedClicked = {
                            if (isFavourite) {
                                removeFavourite(wallpaper.id)
                            } else {
                                addFavourite(
                                    wallpaper,
                                )
                            }
                        },
                    )
                }
            }
            wallpapers.apply {
                val error =
                    when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                error?.let {
                    item {
                        Text(text = "No items found \n Error : " + it.error.message.toString())
                    }
                    item {
                        Text(text = "Swipe down to refresh")
                    }
                }
            }
        }
    }
}
