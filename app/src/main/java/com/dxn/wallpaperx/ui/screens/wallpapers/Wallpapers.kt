package com.dxn.wallpaperx.ui.screens.wallpapers

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.dxn.wallpaperx.MainActivity
import com.dxn.wallpaperx.domain.usecases.SetWallpaper
import com.dxn.wallpaperx.ui.SetWallpaperActivity
import com.dxn.wallpaperx.ui.components.WallpaperCard
import com.dxn.wallpaperx.ui.utils.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalFoundationApi
@Composable
fun Wallpapers(
    navController: NavController,
    viewModel: WallpapersViewModel = hiltViewModel()
) {
    val wallpapers = viewModel.wallpapers.collectAsLazyPagingItems()
    Column(Modifier.fillMaxSize()) {
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {

            this.items(wallpapers.itemCount) { index ->
                wallpapers[index]?.let {
                    WallpaperCard(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(246.dp),
                        wallpaper = it
                    ) {
                        val context = LocalContext.current as MainActivity
                        val encodedUrl = URLEncoder.encode(wallpapers[index]?.wallpaperUrl, StandardCharsets.UTF_8.toString())
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                val intent = Intent(context,SetWallpaperActivity::class.java)
                                intent.putExtra("wallpaper",wallpapers[index])
                                context.startActivity(intent)
//                                navController.navigate(Screen.FullWallpaper.route.plus("/$encodedUrl"))
                            }) {
                            IconButton(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(12.dp),
                                onClick = {

                                }) {
                                Icon(
                                    imageVector = Icons.Rounded.Favorite,
                                    contentDescription = "favourite button",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
