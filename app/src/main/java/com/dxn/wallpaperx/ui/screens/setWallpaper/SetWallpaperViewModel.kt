package com.dxn.wallpaperx.ui.screens.setWallpaper

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dxn.wallpaperx.common.extensions.shortToast
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
            val res = wallpaperUseCase.setWallpaperUseCases(wallpaper, flag)
            isProgressVisible.value = false
            if (res.isSuccess) {
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
            val uri = saveWallpaper(wallpaper)
            uri?.let { application.shortToast("Downloaded \nPictures/wallpaperx") }
        }
    }

    suspend fun saveWallpaper(wallpaper: Wallpaper): Uri? {
        isProgressVisible.value = true
        val uri = wallpaperUseCase.downloadWallpaper(wallpaper).getOrElse {
            application.shortToast("Failure ${it.message}")
            null
        }
        isProgressVisible.value = false
        return uri
    }

    companion object {
        const val TAG = "SetWallpaperViewModel"
    }
}