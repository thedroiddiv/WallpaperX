package com.dxn.wallpaperx.ui.activities.setwallpaper

import android.app.Application
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import com.dxn.wallpaperx.extensions.getBitmap
import com.dxn.wallpaperx.extensions.shortToast
import com.dxn.wallpaperx.ui.activities.main.MainActivityViewModel
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SetWallpaperViewModel
@Inject
constructor(
    private val wallpaperUseCase: WallpaperUseCase,
    private val application: Application
) : ViewModel() {

    val favouriteIds = mutableStateOf(listOf<Int>())
    val isProgressVisible = mutableStateOf(false)

    init {
        loadFavourites()
    }

    private fun loadFavourites() {
        viewModelScope.launch {
            kotlin.runCatching {
                favouriteIds.value = wallpaperUseCase.getFavourites().map { it.id }
                Log.d(TAG, "loadFavourites: ${favouriteIds.value}")
            }.getOrElse {
                Log.d(TAG, "loadFavourites: ${it.message}")
            }
        }
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
            val bitmap = application.getBitmap(wallpaper.wallpaperUrl)
            wallpaperUseCase.downloadWallpaper(bitmap, "IMG${wallpaper.id}.jpg")
            application.shortToast("Downloaded\nPictures/wallpaperx")
        }
    }

    companion object {
        const val TAG = "SetWallpaperViewModel"
    }
}