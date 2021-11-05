package com.dxn.wallpaperx.ui.screens.home.collections

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.dxn.wallpaperx.domain.models.Collection
import com.dxn.wallpaperx.ui.components.CategoryCard
import com.dxn.wallpaperx.ui.navigation.RootScreen

@Composable
fun Collections(
    collections: LazyPagingItems<Collection>,
    listState: LazyListState,
    navController: NavHostController
) {
    LazyColumn(state = listState) {
        items(collections) { collections ->
            collections?.let {
                CategoryCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(16.dp),
                    title = collections.title,
                    backgroundImage = collections.coverPhoto
                ) {

                    navController.navigate(RootScreen.CollectionWallpaper.route.plus("/${it.id}/${it.title}"))
                }
            }
        }
    }
}