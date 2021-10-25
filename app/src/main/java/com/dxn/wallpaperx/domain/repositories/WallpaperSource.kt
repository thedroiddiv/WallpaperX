package com.dxn.wallpaperx.domain.repositories

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dxn.wallpaperx.domain.models.Wallpaper
import java.lang.Exception
import javax.inject.Inject

class WallpaperSource
constructor(
    private val repository: WallpaperRepository,
    private val apiKey : String
) : PagingSource<Int, Wallpaper>() {

    companion object {
        const val TAG = "WallpaperSource"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {
        return try {
            val nextPage = params.key ?: 1
            val wallpapers = repository.getWallpapers(apiKey,nextPage,"")
            LoadResult.Page(
                data = wallpapers,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        } catch (e:Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        return null
    }

}