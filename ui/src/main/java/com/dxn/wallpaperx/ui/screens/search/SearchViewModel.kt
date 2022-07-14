package com.dxn.wallpaperx.ui.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject
constructor(
    private val wallpaperUseCase: WallpaperUseCase
) : ViewModel() {

    private var searchJob: Job? = null
    val wallpapers = mutableStateOf(flowOf<PagingData<Wallpaper>>())

    fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            wallpapers.value = wallpaperUseCase.getWallpapers(query)
        }
    }

}