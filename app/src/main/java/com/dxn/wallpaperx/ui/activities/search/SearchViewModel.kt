package com.dxn.wallpaperx.ui.activities.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import com.dxn.wallpaperx.ui.activities.main.MainActivityViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject
constructor(
    private val wallpaperUseCase: WallpaperUseCase
) : ViewModel() {

    val wallpapers = mutableStateOf(flowOf<PagingData<Wallpaper>>())
    val favouriteIds: MutableState<List<Int>> = mutableStateOf(listOf())

    init {
        loadFavourites()
    }

    fun search(query:String) {
        wallpapers.value = wallpaperUseCase.getWallpapers(query)
    }

    private fun loadFavourites() {
        viewModelScope.launch {
            kotlin.runCatching {
                favouriteIds.value = wallpaperUseCase.getFavourites().map { it.id }
            }.getOrElse {
                Log.d(MainActivityViewModel.TAG, "loadFavourites: ${it.message}")
            }
        }
    }

    fun addFavourite(wallpaper: Wallpaper) {
        viewModelScope.launch {
            wallpaperUseCase.addFavourite(wallpaper)
            loadFavourites()
        }
    }

    fun removeFavourite(id: Int) {
        viewModelScope.launch {
            wallpaperUseCase.removeFavourite(id)
            loadFavourites()
        }
    }

}