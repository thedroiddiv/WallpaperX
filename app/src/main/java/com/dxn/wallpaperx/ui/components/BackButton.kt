package com.dxn.wallpaperx.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    navController:NavHostController
) {

    IconButton(
        modifier = modifier,
        onClick = {
            navController.popBackStack()
        }) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "back button",
            tint = MaterialTheme.colors.onPrimary
        )
    }
}
