package com.dxn.wallpaperx.app.ui.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dxn.wallpaperx.app.ui.screen.home.HomeScreenVM
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.domain.repository.WallpaperRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class SearchScreenVM(private val wallpaperRepository: WallpaperRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    var searchResults = flowOf<PagingData<Wallpaper>>()
        private set

    fun onSearchActiveChange(active: Boolean) {
        _uiState.update { it.copy(isActive = active) }
    }

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun searchWallpapers() {
        runCatching {
            searchResults =
                Pager(
                    config = PagingConfig(pageSize = 20),
                    pagingSourceFactory = {
                        wallpaperRepository.wallpaperSource(
                            uiState.value.query,
                            false,
                        )
                    },
                ).flow.cachedIn(viewModelScope)
        }.getOrElse {
            Log.e(HomeScreenVM.TAG, "searchWallpapers: ${it.message}")
        }
    }
}
