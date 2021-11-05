package com.dxn.wallpaperx.domain.usecases.collections

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dxn.wallpaperx.domain.models.Collection
import com.dxn.wallpaperx.domain.repositories.CollectionSource
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCollections
@Inject
constructor(private val repository: WallpaperRepository) {
    operator fun invoke(): Flow<PagingData<Collection>> {
        return Pager(PagingConfig(pageSize = 20)) {
            CollectionSource(repository)
        }.flow
    }

    companion object {
        private const val TAG = "GetCollections"
    }
}