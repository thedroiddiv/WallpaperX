package com.dxn.wallpaperx.ui.screens.wallpapers

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.di.ResourcesProvider
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.dxn.wallpaperx.domain.repositories.WallpaperSource
import com.dxn.wallpaperx.domain.usecases.GetWallpaper
import com.dxn.wallpaperx.domain.usecases.WallpaperUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel
@Inject
constructor(
    private val repository: WallpaperRepository,
    private val wallpaperUseCase: WallpaperUseCase
) : ViewModel() {

    val wallpapers = wallpaperUseCase.getWallpapers()

}