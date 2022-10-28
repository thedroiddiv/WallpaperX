package com.dxn.wallpaperx.presentation.ui.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import com.dxn.wallpaperx.presentation.ui.components.SearchBar
import com.dxn.wallpaperx.presentation.ui.components.WallpaperCard
import com.dxn.wallpaperx.presentation.ui.theme.Padding

@Composable
fun SearchScreen(
    onSearch: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        var query by remember { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }
        SearchBar(
            searchQuery = query,
            onQueryChange = { query = it },
            onSubmit = {
                onSearch(it)
            },
            focusRequester = focusRequester,
            onNavigateBack = onNavigateBack
        )
        LaunchedEffect(key1 = Unit, block = {
            focusRequester.requestFocus()
        })
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            item(span = { GridItemSpan(maxLineSpan) }) { Spacer(Modifier.height(48.dp)) }
            items(20) {
                WallpaperCard(
                    modifier = Modifier
                        .padding(Padding.small)
                        .height(240.dp),
                    previewUrl = "https://picsum.photos/200/300",
                    wallpaperUrl = "https://picsum.photos/1345/1920",
                    onDownloadClicked = { }
                ) {}
            }
        }
    }
}
