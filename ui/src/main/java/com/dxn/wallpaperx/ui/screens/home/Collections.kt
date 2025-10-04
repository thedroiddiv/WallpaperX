package com.dxn.wallpaperx.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.ui.components.CategoryCard
import com.dxn.wallpaperx.ui.navigation.RootScreen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun Collections(
    collections: LazyPagingItems<Collection>,
    listState: LazyListState,
    navController: NavHostController
) {
    SwipeRefresh(
        modifier =
            Modifier
                .fillMaxSize(),
        state = rememberSwipeRefreshState(isRefreshing = (collections.loadState.refresh is LoadState.Loading)),
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
            collections.refresh()
        },
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
            items(collections.itemCount) { idx ->
                val collection = collections[idx]
                collection?.let {
                    CategoryCard(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                        title = collection.title,
                        backgroundImage = collection.coverPhoto,
                    ) {
                        navController.navigate(RootScreen.CollectionWallpaper.route.plus("/${it.id}/${it.title}"))
                    }
                }
            }
            collections.apply {
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
