package com.dxn.wallpaperx.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dxn.wallpaperx.ui.navigation.HomeScreen

@Composable
fun BottomBar(
    screens: List<HomeScreen>,
    navController: NavHostController,
    currentRoute: String?
) {
    Row(
        modifier = Modifier.background(MaterialTheme.colors.primary).height(54.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        screens.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            currentRoute?.let { popUpTo(it) { inclusive = true } }
                        }
                    }
                },
                label = { Text(text = screen.title) },
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = screen.title)
                },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.onPrimary.copy(0.4f),
                alwaysShowLabel = false
            )
        }
    }
}