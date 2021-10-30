package com.dxn.wallpaperx.ui.activities.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.ui.activities.search.components.SearchBar
import com.dxn.wallpaperx.ui.components.WallpaperList
import com.dxn.wallpaperx.ui.theme.WallpaperXTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchActivity : ComponentActivity() {
    @ExperimentalCoilApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WallpaperXTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val viewModel: SearchViewModel = viewModel()
                    val dataFlow by remember { viewModel.wallpapers }
                    val wallpapers = dataFlow.collectAsLazyPagingItems()
                    val favouriteIds by remember { viewModel.favouriteIds }

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                modifier = Modifier,
                                backgroundColor = MaterialTheme.colors.primary
                            ) {
                                SearchBar(
                                    leadingIcon =
                                    {
                                        IconButton(onClick = {
                                            this@SearchActivity.finish()
                                        }) {
                                            Icon(
                                                imageVector = Icons.Rounded.ArrowBack,
                                                contentDescription = "back",
                                                tint = MaterialTheme.colors.onPrimary
                                            )
                                        }
                                    },
                                    onSearch = { viewModel.search(it) }
                                )
                            }
                        }
                    ) {
                        WallpaperList(
                            wallpapers = wallpapers,
                            favouriteIds = favouriteIds,
                            addFavourite = { wallpaper ->
                                viewModel.addFavourite(wallpaper)
                            },
                            removeFavourite = { id ->
                                viewModel.removeFavourite(id)
                            }
                        )
                    }
                }
            }
        }
    }
}


