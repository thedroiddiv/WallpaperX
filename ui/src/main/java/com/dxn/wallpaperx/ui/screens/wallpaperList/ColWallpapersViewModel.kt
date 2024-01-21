package com.dxn.wallpaperx.ui.screens.wallpaperList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import com.dxn.wallpaperx.ui.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColWallpapersViewModel
    @Inject
    constructor(
        private val wallpaperUseCase: WallpaperUseCase,
    ) : ViewModel() {
        private var wallJob: Job? = null
        var wallpapers = flowOf<PagingData<Wallpaper>>()

        fun loadWallpapers(collectionId: String) {
            wallJob?.cancel()
            wallJob =
                viewModelScope.launch {
                    kotlin.runCatching {
                        wallpapers = wallpaperUseCase.getWallpapersByCollection(collectionId)
                    }.getOrElse {
                        Log.e(MainViewModel.TAG, "loadFavourites: ${it.message}")
                    }
                }
        }
    }
