package com.dxn.wallpaperx.ui.screens.setWallpaper

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import com.dxn.wallpaperx.common.extensions.getBitmap
import com.dxn.wallpaperx.common.extensions.shortToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log2

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
            val res = wallpaperUseCase.setWallpaperUseCases(wallpaper, flag)
            isProgressVisible.value = false
            if(res.isSuccess) {
                application.shortToast("Applied!")
            } else {
                res.getOrElse {
                    application.shortToast("Failure ${it.message}")
                }
            }
        }
    }

    fun downloadWallpaper(wallpaper: Wallpaper) {
        viewModelScope.launch {
            val res = wallpaperUseCase.downloadWallpaper(wallpaper)
            if(res.isSuccess) {
                application.shortToast("Downloaded\nPictures/wallpaperx")
            }
            res.getOrElse {
                application.shortToast("Failure ${it.message}")
            }
        }
    }

    companion object {
        const val TAG = "SetWallpaperViewModel"
    }
}