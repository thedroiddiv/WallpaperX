package com.dxn.wallpaperx.domain.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.domain.repository.WallpaperRepository

class WallpaperSource
constructor(
    private val repository: WallpaperRepository,
    private val query: String,
    private val isCollection: Boolean
) : PagingSource<Int, Wallpaper>() {

    companion object {
        const val TAG = "WallpaperSource"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {
        return kotlin.runCatching {
            val nextPage = params.key ?: 1
            val wallpapers =
                if (isCollection) repository.getWallpapersByCollection(query, nextPage) else
                    repository.getWallpapers(nextPage, query)
            LoadResult.Page(
                data = wallpapers,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        }.getOrElse {
            Log.e(TAG, "load: ${it.message}")
            LoadResult.Error(it)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        return null
    }
}
