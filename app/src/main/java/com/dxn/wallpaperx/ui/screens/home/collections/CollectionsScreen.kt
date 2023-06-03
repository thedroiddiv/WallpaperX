package com.dxn.wallpaperx.ui.screens.home.collections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.dxn.wallpaperx.ui.screens.home.HomeScreenVM

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CollectionsScreen(
    viewModel: HomeScreenVM
) {
    val collections = viewModel.collections.collectAsLazyPagingItems()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(collections.itemCount) { idx ->
            val collection = collections[idx]
            collection?.let {
                CollectionCard(it) {
                }
            }
        }
    }
}
