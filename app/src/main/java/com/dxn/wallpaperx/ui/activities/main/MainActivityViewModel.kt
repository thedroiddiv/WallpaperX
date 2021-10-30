package com.dxn.wallpaperx.ui.activities.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.dxn.wallpaperx.domain.models.SavedWallpaper
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel
@Inject
constructor(
    private val wallpaperUseCase: WallpaperUseCase
) : ViewModel() {



    var wallpapers = flowOf<PagingData<Wallpaper>>()
    val favourites: MutableState<List<Wallpaper>> = mutableStateOf(listOf())

    init {
        loadWallpapers()
        loadFavourites()
    }

    private fun loadWallpapers() {
        viewModelScope.launch {
            kotlin.runCatching {
                wallpapers = wallpaperUseCase.getWallpapers("wallpaper")
            }
        }
    }

    fun loadFavourites() {
        viewModelScope.launch {
            kotlin.runCatching {
                favourites.value = wallpaperUseCase.getFavourites()
            }.getOrElse {
                Log.d(TAG, "loadFavourites: ${it.message}")
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

    companion object {
        const val TAG = "MainActivityViewModel"
    }
}