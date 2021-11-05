package com.dxn.wallpaperx.data.remote.unsplash

import com.dxn.wallpaperx.R
import com.dxn.wallpaperx.common.helpers.ResourcesProvider
import com.dxn.wallpaperx.data.remote.RemoteRepository
import com.dxn.wallpaperx.data.remote.unsplash.models.collection.CollectionDto
import com.dxn.wallpaperx.data.remote.unsplash.models.image.ImageDto
import com.dxn.wallpaperx.domain.models.Collection
import com.dxn.wallpaperx.domain.models.Wallpaper

class UnsplashRepository(
    private val unsplashApi: UnsplashApi,
    private val resourcesProvider: ResourcesProvider
) : RemoteRepository {

    private val apikey = resourcesProvider.getString(R.string.unsplash_api_key)


    override suspend fun getWallpapers(page: Int, query: String): List<Wallpaper> {
        return unsplashApi.getImages(
            query = query,
            page = page,
            apikey = resourcesProvider.getString(R.string.unsplash_api_key)
        ).results.map { imageDtoToWallpaper(it) }
    }

    override suspend fun getWallpaper(id: String): Wallpaper {
        return imageDtoToWallpaper(unsplashApi.getImage())
    }

    override suspend fun getCollections(page: Int): List<Collection> =
        unsplashApi.getCollection(page, apikey = apikey).map { collectionDtoToCollection(it) }

    override suspend fun getWallpapersByCollection(
        collectionId: String,
        page: Int
    ): List<Wallpaper> =
        unsplashApi.getPhotosByCollection(collectionId, page, apikey = apikey)
            .map { imageDtoToWallpaper(it) }
}


fun imageDtoToWallpaper(imageDto: ImageDto): Wallpaper {
    return Wallpaper(
        id = imageDto.id,
        previewUrl = imageDto.urls.thumb,
        smallUrl = imageDto.urls.small,
        wallpaperUrl = imageDto.urls.full,
        user = imageDto.user.name,
        userImageURL = imageDto.user.profile_image.small
    )
}

fun collectionDtoToCollection(collectionDto: CollectionDto): Collection {
    return Collection(
        id = collectionDto.id,
        title = collectionDto.title,
        totalPhotos = collectionDto.total_photos,
        coverPhoto = collectionDto.cover_photo.urls.regular,
        tags = collectionDto.tags.map { it.title }
    )
}