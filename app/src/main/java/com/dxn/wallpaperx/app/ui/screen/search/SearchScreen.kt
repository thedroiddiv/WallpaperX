package com.dxn.wallpaperx.app.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.dxn.wallpaperx.app.ui.components.WallpaperCard
import com.dxn.wallpaperx.app.ui.nav.Screen
import com.dxn.wallpaperx.data.model.Wallpaper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onQueryChange: (String) -> Unit,
    onSearchActiveChange: (Boolean) -> Unit,
    onSearch: () -> Unit,
    searchResults: LazyPagingItems<Wallpaper>,
    onFavClick: (Wallpaper) -> Unit,
    onWallpaperClick: (Wallpaper) -> Unit,
    navigateUp: () -> Unit,
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DockedSearchBar(
            modifier = Modifier,
            query = uiState.query,
            onQueryChange = onQueryChange,
            onSearch = { onSearch() },
            active = uiState.isActive,
            onActiveChange = onSearchActiveChange,
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = Screen.Search.icon),
                        contentDescription = stringResource(id = Screen.Search.title),
                    )
                }
            },
            leadingIcon = {
                IconButton(onClick = {
                    if (uiState.isActive) {
                        onQueryChange("")
                        onSearchActiveChange(false)
                    } else {
                        navigateUp()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(id = Screen.Search.title),
                    )
                }
            },
        ) {
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(searchResults.itemCount) { idx ->
                searchResults[idx]?.let { wallpaper ->
                    val isFavourite = uiState.favourites.any { it.id == wallpaper.id }
                    WallpaperCard(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                        previewUrl = wallpaper.smallUrl,
                        isFav = isFavourite,
                        onFavClick = { onFavClick(wallpaper) },
                        onClick = { onWallpaperClick(wallpaper) },
                    )
                }
            }
        }
    }
}
