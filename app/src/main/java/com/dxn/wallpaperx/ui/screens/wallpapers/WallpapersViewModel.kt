package com.dxn.wallpaperx.ui.screens.wallpapers

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.di.ResourcesProvider
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.dxn.wallpaperx.domain.repositories.WallpaperSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel
@Inject
constructor(
    private val repository: WallpaperRepository,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    val wallpapers = Pager(PagingConfig(pageSize = 20)) {
        WallpaperSource(repository,resourcesProvider.getString(R.string.pixabay_api_key))
    }.flow

}