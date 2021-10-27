package com.dxn.wallpaperx.ui.activities.setwallpaper

import android.app.Application
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SetWallpaperViewModel
@Inject
constructor(
    private val useCase: WallpaperUseCase,
    application: Application
) : AndroidViewModel(application) {
    fun saveWallpaper(wallpaper: Wallpaper): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            val loader = ImageLoader(getApplication())
            val request = ImageRequest.Builder(getApplication())
                .data(wallpaper.wallpaperUrl)
                .allowHardware(false) // Disable hardware bitmaps.
                .build()
            val result = (loader.execute(request) as SuccessResult).drawable
            val bitmap = (result as BitmapDrawable).bitmap
            useCase.saveWallpaper(bitmap, "IMG${wallpaper.id}.jpg")
        }
    }
}