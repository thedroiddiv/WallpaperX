package com.dxn.wallpaperx.data.remote.pixabay

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.dxn.wallpaperx.data.BuildConfig
import com.dxn.wallpaperx.data.local.LocalDatabase
import com.dxn.wallpaperx.data.local.entities.WallpaperEntity
import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.data.remote.RemoteRepository
import com.dxn.wallpaperx.data.remote.RemoteWallpaperMediator
import com.dxn.wallpaperx.data.remote.pixabay.models.Hit

const val PAGE_SIZE = 20

class PixabayRepository(
    private val api: PixabayApi,
    private val localDatabase: LocalDatabase,
) : RemoteRepository {
    private val apiKey: String = BuildConfig.PIXABAY_API_KEY

    override suspend fun getWallpapers(
        page: Int,
        query: String,
    ): List<Wallpaper> {
        return api.searchImage(
            apiKey = apiKey,
            query = query,
            page = page,
        ).hits.map(::hitToWallpaper)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getWallpapers(query: String): Pager<Int, WallpaperEntity> {
        val mediator = RemoteWallpaperMediator(api, localDatabase, query)
        return Pager(
            config =
                PagingConfig(
                    pageSize = PAGE_SIZE,
                    prefetchDistance = 10,
                    initialLoadSize = PAGE_SIZE,
                ),
            remoteMediator = mediator,
            pagingSourceFactory = {
                localDatabase.wallpaperDao.getAll()
            },
        )
    }

    override suspend fun getWallpaper(id: String): Wallpaper {
        return api.searchImage(
            apiKey = apiKey,
            id = id,
        ).hits.map(::hitToWallpaper).first()
    }

    override suspend fun getCollections(page: Int): List<Collection> {
        return if (page == 1) collections else listOf()
    }

    override suspend fun getWallpapersByCollection(
        collectionId: String,
        page: Int,
    ): List<Wallpaper> {
        return api.searchImage(
            apiKey = apiKey,
            category = collectionId,
        ).hits.map(::hitToWallpaper)
    }
}

fun hitToWallpaper(imageDto: Hit): Wallpaper {
    return Wallpaper(
        id = imageDto.id.toString(),
        previewUrl = imageDto.previewURL,
        smallUrl = imageDto.webformatURL,
        wallpaperUrl = imageDto.largeImageURL,
        user = imageDto.user,
        userImageURL = imageDto.userImageURL,
    )
}
