package com.dxn.wallpaperx.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val wallpaperUseCase: WallpaperUseCase
) : ViewModel() {

    private var favJob: Job? = null
    private var wallJob: Job? = null
    private var collJob: Job? = null
    var wallpapers = flowOf<PagingData<Wallpaper>>()
    var collections = flowOf<PagingData<Collection>>()
    val favourites: MutableState<List<Wallpaper>> = mutableStateOf(listOf())

    init {
        loadWallpapers()
        loadFavourites()
        loadCollections()
    }

    private fun loadCollections() {
        collJob?.cancel()
        collJob = viewModelScope.launch {
            kotlin.runCatching {
                collections = wallpaperUseCase.getCollections()
                Log.d(TAG, "loadCollections: $collections")
            }.getOrElse {
                Log.e(TAG, "loadFavourites: ${it.message}")
            }
        }
    }

    private fun loadWallpapers() {
        wallJob?.cancel()
        wallJob = viewModelScope.launch {
            kotlin.runCatching {
                wallpapers = wallpaperUseCase.getWallpapers("wallpaper")
            }.getOrElse {
                Log.e(TAG, "loadFavourites: ${it.message}")
            }
        }
    }

    private fun loadFavourites() = kotlin.runCatching {
        favJob?.cancel()
        favJob = wallpaperUseCase.getFavourites()
            .onEach { favourites.value = it }
            .launchIn(viewModelScope)
    }.getOrElse {
        Log.e(TAG, "loadFavourites: ${it.message}")
    }

    fun addFavourite(wallpaper: Wallpaper) = kotlin.runCatching {
        viewModelScope.launch { wallpaperUseCase.addFavourite(wallpaper) }
    }.getOrElse {
        Log.e(TAG, "loadFavourites: ${it.message}")
    }

    fun removeFavourite(id: String) = kotlin.runCatching {
        viewModelScope.launch { wallpaperUseCase.removeFavourite(id) }
    }.getOrElse {
        Log.e(TAG, "loadFavourites: ${it.message}")
    }

    companion object {
        const val TAG = "MainActivityViewModel"
    }
}
