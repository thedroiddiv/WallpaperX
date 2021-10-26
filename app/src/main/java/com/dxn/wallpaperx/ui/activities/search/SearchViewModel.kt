package com.dxn.wallpaperx.ui.activities.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject
constructor(
    private val wallpaperUseCase: WallpaperUseCase
) : ViewModel() {

    val wallpapers = mutableStateOf(wallpaperUseCase.getWallpapers(""))
    fun search(query:String) {
        wallpapers.value = wallpaperUseCase.getWallpapers(query)
    }

}