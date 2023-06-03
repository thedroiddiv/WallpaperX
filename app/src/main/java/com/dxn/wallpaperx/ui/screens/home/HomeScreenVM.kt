package com.dxn.wallpaperx.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.dxn.wallpaperx.domain.repository.WallpaperRepository
import com.dxn.wallpaperx.domain.source.CollectionSource
import com.dxn.wallpaperx.domain.source.WallpaperSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM
@Inject
constructor(
    private val repository: WallpaperRepository
) : ViewModel() {

    val wallpapers = Pager(PagingConfig(pageSize = 20)) {
        WallpaperSource(repository, "wallpaper", false)
    }.flow.cachedIn(viewModelScope)

    val collections = Pager(PagingConfig(pageSize = 20)) {
        CollectionSource(repository)
    }.flow.cachedIn(viewModelScope)
}
