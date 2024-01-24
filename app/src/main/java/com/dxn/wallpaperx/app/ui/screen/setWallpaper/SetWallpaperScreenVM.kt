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
    private val savedStateHandle: SavedStateHandle,
    private val wallpaperRepository: WallpaperRepository,
) : ViewModel() {
    private val wallpaperId: String? = savedStateHandle["wallpaperId"]

    private val _uiState = MutableStateFlow<SetWallpaperUiState>(SetWallpaperUiState.INITIAL)
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
}
