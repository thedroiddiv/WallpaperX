package com.dxn.wallpaperx.ui.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dxn.wallpaperx.ui.components.WallpaperList

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Search(
    navController: NavHostController
) {
    val viewModel : SearchViewModel = hiltViewModel()
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
                            navController.popBackStack()
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
            },
            navController = navController
        )
    }
}

@Composable
fun SearchBar(
    leadingIcon: @Composable () -> Unit,
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .fillMaxWidth(1f),
            textStyle = MaterialTheme.typography.body1,
            leadingIcon = leadingIcon,
            trailingIcon = {
                IconButton(onClick = {
                    query = ""
                    focusManager.clearFocus()
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Search",
                        tint = MaterialTheme.colors.onPrimary.copy(0.5f)
                    )
                }
            },
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onPrimary,
                cursorColor = MaterialTheme.colors.onPrimary,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colors.onPrimary.copy(0.7f),
                unfocusedIndicatorColor = MaterialTheme.colors.onPrimary.copy(0.6f),
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch(query)
                focusManager.clearFocus()
            })
        )
    }
}