package com.dxn.wallpaperx.app.ui.screen.home

import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.data.model.Wallpaper

data class HomeUiState(
    val favourites: List<Wallpaper>, // Can be optimised by {id: wallpaper,...}
    // val wallpapers: PagingData<Wallpaper>, // Can we use plain list? Maybe wallpapers.itemSnapshotList.items?
    val collections: List<Collection>
)
