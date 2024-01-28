package com.dxn.wallpaperx.app.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.domain.repository.WallpaperRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenVM(
    private val wallpaperRepository: WallpaperRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    var wallpapers =
        wallpaperRepository.getWallpapers("wallpaper").flow.map { pagerData ->
            pagerData.map {
                Wallpaper(
                    id = it.id,
                    previewUrl = it.previewUrl,
                    smallUrl = it.smallUrl,
                    wallpaperUrl = it.wallpaperUrl,
                    user = it.user,
                    userImageURL = it.userImageURL,
                )
            }
        }.cachedIn(viewModelScope)

    init {
        loadWallpapers()
        loadCollections()
        loadFavourites()
    }

    private fun loadWallpapers() {
//        runCatching {
//            wallpapers =
//                Pager(
//                    config = PagingConfig(pageSize = 20),
//                    pagingSourceFactory = {
//                        wallpaperRepository.wallpaperSource(
//                            "wallpaper",
//                            false,
//                        )
//                    },
//                ).flow.cachedIn(viewModelScope)
//        }.getOrElse {
//            Log.e(TAG, "loadWallpapers: ${it.message}")
//        }
    }

    private fun loadCollections() {
    }

    private fun loadFavourites() {
        viewModelScope.launch {
            wallpaperRepository.getFavourites().collect { favourites ->
                _uiState.update { it.copy(favourites = favourites) }
            }
        }
    }

    fun addToFavourites(wallpaper: Wallpaper) {
        viewModelScope.launch {
            wallpaperRepository.addFavourite(wallpaper)
        }
    }

    companion object {
        const val TAG = "HomeScreenVM"
    }
}
