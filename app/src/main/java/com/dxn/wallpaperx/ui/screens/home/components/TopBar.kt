package com.dxn.wallpaperx.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dxn.wallpaperx.ui.navigation.HomeScreen
import com.dxn.wallpaperx.ui.navigation.RootScreen


@Composable
fun TopBar(
    currentDest: HomeScreen?,
    navController: NavHostController
) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = currentDest?.title ?: "",style = MaterialTheme.typography.h5, fontWeight = FontWeight.Light)
            IconButton(onClick = {
                navController.navigate(RootScreen.Search.route)
            }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search"
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.onPrimary.copy(0.1f))
        )
    }

}