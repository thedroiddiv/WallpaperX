package com.dxn.wallpaperx.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.dxn.wallpaperx.app.ui.nav.Screen

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentScreen: Screen,
    screens: List<Screen>,
    onClick: (Screen) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = stringResource(screen.title)
                    )
                },
                label = { Text(stringResource(screen.title)) },
                selected = screen.route == currentScreen.route,
                onClick = {
                    if (screen.route != currentScreen.route) {
                        onClick(screen)
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}