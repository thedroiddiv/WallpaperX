package com.dxn.wallpaperx.domain.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dxn.wallpaperx.domain.models.Wallpaper

class WallpaperSource
constructor(
    private val repository: WallpaperRepository,
    private val apiKey: String,
    private val query: String
) : PagingSource<Int, Wallpaper>() {

    companion object {
        const val TAG = "WallpaperSource"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {
        return kotlin.runCatching {
            val nextPage = params.key ?: 1
            val wallpapers =
                repository.getWallpapers(apiKey, nextPage, query)
            LoadResult.Page(
                data = wallpapers,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        }.getOrThrow()
    }

    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        return null
    }

}