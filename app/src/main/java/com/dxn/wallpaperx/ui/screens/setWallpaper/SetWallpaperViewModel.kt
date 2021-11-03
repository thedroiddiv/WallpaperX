package com.dxn.wallpaperx.ui.screens.setWallpaper

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import com.dxn.wallpaperx.common.extensions.getBitmap
import com.dxn.wallpaperx.common.extensions.shortToast
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

    val isProgressVisible = mutableStateOf(false)

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