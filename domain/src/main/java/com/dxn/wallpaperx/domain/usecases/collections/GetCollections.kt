package com.dxn.wallpaperx.domain.usecases.collections

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.domain.repository.WallpaperRepository
import com.dxn.wallpaperx.domain.source.CollectionSource
import kotlinx.coroutines.flow.Flow

class GetCollections
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
