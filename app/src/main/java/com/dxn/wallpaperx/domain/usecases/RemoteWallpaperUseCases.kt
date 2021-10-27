package com.dxn.wallpaperx.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.helpers.ResourcesProvider
import com.dxn.wallpaperx.domain.models.Wallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import com.dxn.wallpaperx.domain.repositories.WallpaperSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWallpaper
@Inject
constructor(
    private val repository: WallpaperRepository,
    private val resourcesProvider: ResourcesProvider
) {
    suspend operator fun invoke(id: Int): Wallpaper {
        return repository.getWallpaper(resourcesProvider.getString(R.string.pixabay_api_key), id)
    }
}

class GetWallpapers
@Inject
constructor(
    private val repository: WallpaperRepository,
    private val resourcesProvider: ResourcesProvider
) {
    operator fun invoke(query: String): Flow<PagingData<Wallpaper>> {
        return Pager(PagingConfig(pageSize = 20)) {
            WallpaperSource(
                repository,
                resourcesProvider.getString(R.string.pixabay_api_key),
                query
            )
        }.flow
    }
}