package com.dxn.wallpaperx.ui.components.appbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.ui.navigation.BottomNavDestinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    destination: BottomNavDestinations,
    scrollBehavior: TopAppBarScrollBehavior,
    onSearch: () -> Unit,
    onSettings: () -> Unit
) {
    LargeTopAppBar(
        title = {
            Text(
                stringResource(id = destination.title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(onClick = onSearch) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Localized description"
                )
            }
            IconButton(onClick = onSettings) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_setting),
                    contentDescription = "Localized description"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}
