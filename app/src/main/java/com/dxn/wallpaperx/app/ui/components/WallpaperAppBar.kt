package com.dxn.wallpaperx.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.dxn.wallpaperx.app.R
import com.dxn.wallpaperx.app.ui.nav.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperAppBar(
    destination: Screen,
    navigateUp: () -> Unit,
    toSettings: () -> Unit,
    toSearch: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    when (destination) {
        Screen.Wallpapers, Screen.Collections, Screen.Favourites -> {
            LargeTopAppBar(
                title = { Text(text = stringResource(destination.title)) },
                actions = {
                    IconButton(onClick = toSettings) {
                        Icon(
                            painter = painterResource(id = Screen.Settings.icon),
                            contentDescription = stringResource(id = Screen.Settings.title),
                        )
                    }
                    IconButton(onClick = toSearch) {
                        Icon(
                            painter = painterResource(id = Screen.Search.icon),
                            contentDescription = stringResource(id = Screen.Search.title),
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }

        Screen.Search -> {}
        Screen.SetWallpaper -> {}
        Screen.Gallery -> {}
        Screen.Settings -> {
            LargeTopAppBar(
                title = { Text(text = stringResource(destination.title)) },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    }
}
