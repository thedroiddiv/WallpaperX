package com.dxn.wallpaperx.domain.repository

import android.graphics.Bitmap
import android.net.Uri
import com.dxn.wallpaperx.data.local.LocalRepository
import com.dxn.wallpaperx.data.local.favourites.FavouriteEntity
import com.dxn.wallpaperx.data.model.Collection
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.data.remote.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WallpaperRepositoryImpl
    constructor(
        private val remoteRepository: RemoteRepository,
        private val localRepository: LocalRepository
    ) : WallpaperRepository {

        companion object {
            const val TAG = "WallpaperRepositoryImpl"
        }

        override suspend fun getWallpapers(page: Int, query: String) =
            remoteRepository.getWallpapers(page, query)

        override suspend fun getWallpaper(id: String) =
            remoteRepository.getWallpaper(id)

        override suspend fun getCollections(page: Int): List<Collection> =
            remoteRepository.getCollections(page)

        override suspend fun getWallpapersByCollection(
            collectionId: String,
            page: Int
        ): List<Wallpaper> = remoteRepository.getWallpapersByCollection(collectionId, page)

        override suspend fun downloadWallpaper(bitmap: Bitmap, displayName: String): Uri? =
            localRepository.downloadWallpaper(bitmap, displayName)

        override suspend fun addFavourite(wallpaper: Wallpaper): Boolean =
            localRepository.addToFavourites(
                wallpaperToFavouriteEntity(wallpaper),
            )

        override suspend fun removeFavourite(id: String): Boolean =
            localRepository.removeFavourite(id)

        override fun getFavourites(): Flow<List<Wallpaper>> =
            localRepository.getFavourites()
                .map { wallpapers -> wallpapers.map { favEntityToWallpaper(it) } }
    }

fun favEntityToWallpaper(favouriteEntity: FavouriteEntity): Wallpaper {
    return Wallpaper(
        favouriteEntity.id,
        favouriteEntity.previewUrl,
        favouriteEntity.wallpaperUrl,
        favouriteEntity.smallUrl,
        favouriteEntity.user,
        favouriteEntity.userImageURL,
    )
}

fun wallpaperToFavouriteEntity(wallpaper: Wallpaper): FavouriteEntity {
    return FavouriteEntity(
        wallpaper.id,
        wallpaper.previewUrl,
        wallpaper.wallpaperUrl,
        wallpaper.smallUrl,
        wallpaper.user,
        wallpaper.userImageURL,
    )
}
