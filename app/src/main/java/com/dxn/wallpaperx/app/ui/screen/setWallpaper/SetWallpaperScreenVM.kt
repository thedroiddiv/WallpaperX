package com.dxn.wallpaperx.app.ui.screen.setWallpaper

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dxn.wallpaperx.domain.repository.WallpaperRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SetWallpaperScreenVM(
    savedStateHandle: SavedStateHandle,
    private val wallpaperRepository: WallpaperRepository,
    private val wallpaperManager: WallpaperManager,
) : ViewModel() {
    private val wallpaperId: String? = savedStateHandle["wallpaperId"]

    private val _uiState = MutableStateFlow(SetWallpaperUiState.INITIAL)
    val uiState = _uiState.asStateFlow()

    init {
        loadWallpaper()
    }

    fun loadWallpaper() {
        viewModelScope.launch {
            wallpaperId?.let { id ->
                val wallpaper = wallpaperRepository.getWallpaper(id)
                _uiState.update { it.copy(wallpaper = wallpaper) }
            }
        }
    }

    fun setWallpaper(flag: Int) {
        _uiState.update { it.copy(loading = true) }
        uiState.value.wallpaper?.let { wallpaper ->
            viewModelScope.launch {
                wallpaperManager.setWallpaper(wallpaper, flag)
                _uiState.update { it.copy(loading = true) }
            }
        }
    }

    fun download() {
        TODO("Not yet implemented")
    }

    fun setLockScreenWallpaper() {
        TODO("Not yet implemented")
    }

    fun setHomeScreenWallpaper() {
        TODO("Not yet implemented")
    }

    fun addToFavourites() {
        TODO("Not yet implemented")
    }

    fun share() {
        TODO("Not yet implemented")
    }
}
