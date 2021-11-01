package com.dxn.wallpaperx.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.common.helpers.ResourcesProvider
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.dxn.wallpaperx.domain.repositories.WallpaperSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWallpaper
@Inject
constructor(
    private val repository: WallpaperRepository,
) {
    suspend operator fun invoke(id: String): Wallpaper {
        return repository.getWallpaper( id)
    }
}

class GetWallpapers
@Inject
constructor(
    private val repository: WallpaperRepository,
) {
    operator fun invoke(query: String): Flow<PagingData<Wallpaper>> {
        return Pager(PagingConfig(pageSize = 20)) {
            WallpaperSource(repository, query)
        }.flow
    }
}