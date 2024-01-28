package com.dxn.wallpaperx.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dxn.wallpaperx.data.BuildConfig
import com.dxn.wallpaperx.data.local.LocalDatabase
import com.dxn.wallpaperx.data.local.entities.RemoteKeyEntity
import com.dxn.wallpaperx.data.local.entities.WallpaperEntity
import com.dxn.wallpaperx.data.mapper.toWallpaperEntity
import com.dxn.wallpaperx.data.remote.pixabay.PixabayApi
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class RemoteWallpaperMediator(
    private val pixabayApi: PixabayApi,
    private val localDatabase: LocalDatabase,
    private val query: String,
) : RemoteMediator<Int, WallpaperEntity>() {
    private val apiKey = BuildConfig.PIXABAY_API_KEY

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

        return if (System.currentTimeMillis() - (
                localDatabase.keyDao.getCreationTime()
                    ?: 0
            ) < cacheTimeout
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, WallpaperEntity>,
    ): MediatorResult {
        val page =
            when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                    prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                    nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

        try {
            val apiResponse =
                pixabayApi.searchImage(
                    apiKey = apiKey,
                    query = query,
                    page = page,
                )
            val wallpapers = apiResponse.hits.map { it.toWallpaperEntity(page) }
            val endOfPaginationReached = wallpapers.isEmpty()

            localDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    localDatabase.keyDao.clear()
                    localDatabase.wallpaperDao.clear()
                }

                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1

                val remoteKeys =
                    wallpapers.map {
                        RemoteKeyEntity(
                            wallpaperId = it.id,
                            prevKey = prevKey,
                            currentPage = page,
                            nextKey = nextKey,
                        )
                    }

                localDatabase.keyDao.insertAll(remoteKeys)
                localDatabase.wallpaperDao.insertAll(wallpapers)
            }
            return MediatorResult.Success(endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: retrofit2.HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, WallpaperEntity>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                localDatabase.keyDao.getRemoteKeyByWallpaperId(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, WallpaperEntity>): RemoteKeyEntity? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            localDatabase.keyDao.getRemoteKeyByWallpaperId(movie.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, WallpaperEntity>): RemoteKeyEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            localDatabase.keyDao.getRemoteKeyByWallpaperId(movie.id)
        }
    }
}
