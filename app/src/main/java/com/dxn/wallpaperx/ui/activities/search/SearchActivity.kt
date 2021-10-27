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
import coil.annotation.ExperimentalCoilApi
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
                        WallpaperList(dataFlow = dataFlow) {

                        }
                    }
                }
            }
        }
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