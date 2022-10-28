package com.dxn.wallpaperx.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomNavigation(
    selectedDestination: String,
    navigateToTopLevelDestination: (WallXNavDestination) -> Unit
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        BOTTOM_NAV_DESTINATIONS.forEach { destination ->
            NavigationBarItem(
                selected = selectedDestination == destination.route,
                onClick = { navigateToTopLevelDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}
