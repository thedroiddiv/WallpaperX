package com.dxn.wallpaperx.ui.screens.setWallpaper

import android.app.Application
import android.app.WallpaperManager
import android.graphics.Bitmap
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
            wallpaperUseCase.setWallpaperUseCases(wallpaper, flag).getOrElse {
                application.shortToast("Failure ${it.message}")
                null
            }.let {
                application.shortToast("Applied  ${if (flag == WallpaperManager.FLAG_SYSTEM) "Home" else "Lock"}  Screen Wallpaper")
            }
            isProgressVisible.value = false
        }
    }

    fun setWallpaper(bitmap: Bitmap, flag: Int) {
        isProgressVisible.value = true
        viewModelScope.launch {
            wallpaperUseCase.setWallpaperUseCases(bitmap, flag).getOrElse {
                application.shortToast("Failure ${it.message}")
                null
            }.let {
                application.shortToast("Applied  ${if (flag == WallpaperManager.FLAG_SYSTEM) "Home" else "Lock"}  Screen Wallpaper")
            }
            isProgressVisible.value = false
        }
    }

    fun downloadWallpaper(wallpaper: Wallpaper) {
        isProgressVisible.value = true
        viewModelScope.launch {
            val uri = saveWallpaper(wallpaper)
            uri?.let { application.shortToast("Downloaded \nPictures/wallpaperx") }
            isProgressVisible.value = false
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