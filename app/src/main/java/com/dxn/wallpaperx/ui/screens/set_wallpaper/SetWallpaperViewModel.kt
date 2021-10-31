package com.dxn.wallpaperx.ui.screens.set_wallpaper

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import com.dxn.wallpaperx.extensions.getBitmap
import com.dxn.wallpaperx.extensions.shortToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetWallpaperViewModel
@Inject
constructor(
    private val wallpaperUseCase: WallpaperUseCase,
    private val application: Application
) : ViewModel() {

    var favJob : Job? = null
    val favouriteIds = mutableStateOf(listOf<Int>())
    val isProgressVisible = mutableStateOf(false)

    init {
        loadFavourites()
    }

    private fun loadFavourites() {
        favJob?.cancel()
        favJob = wallpaperUseCase.getFavourites()
            .onEach { favouriteIds.value = it.map { wallpaper -> wallpaper.id  } }
            .launchIn(viewModelScope)
    }


    fun removeFavourite(id: Int) {
        viewModelScope.launch {
            wallpaperUseCase.removeFavourite(id)
        }
        loadFavourites()
    }


    fun addFavourite(wallpaper: Wallpaper) {
        viewModelScope.launch {
            wallpaperUseCase.addFavourite(wallpaper)
            loadFavourites()
        }
    }

    fun setWallpaper(wallpaper: Wallpaper, flag: Int) {
        isProgressVisible.value = true
        viewModelScope.launch {
            wallpaperUseCase.setWallpaperUseCases(wallpaper, flag)
            isProgressVisible.value = false
            application.shortToast("Wallpaper applied!")
        }
    }

    fun downloadWallpaper(wallpaper: Wallpaper) {
        viewModelScope.launch {
            val bitmap = application.getBitmap(wallpaper.wallpaperUrl!!)
            wallpaperUseCase.downloadWallpaper(bitmap, "IMG${wallpaper.id}.jpg")
            application.shortToast("Downloaded\nPictures/wallpaperx")
        }
    }

    companion object {
        const val TAG = "SetWallpaperViewModel"
    }
}