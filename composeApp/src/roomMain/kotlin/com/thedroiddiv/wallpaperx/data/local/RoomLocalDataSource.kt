package com.thedroiddiv.wallpaperx.data.local

import com.thedroiddiv.wallpaperx.data.model.Wallpaper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Room-backed implementation of [LocalDataSource].
 * Accepts a fully-built [WallpaperDatabase] so the platform-specific
 * builder (which requires Context on Android, a file path on iOS/JVM)
 * stays out of shared code.
 */
class RoomLocalDataSource(db: WallpaperDatabase) : LocalDataSource {

    private val dao: FavouriteDao = db.favouriteDao()

    override suspend fun addFavourite(wallpaper: Wallpaper) =
        dao.insert(wallpaper.toEntity())

    override suspend fun removeFavourite(id: String) =
        dao.delete(id)

    override fun getFavourites(): Flow<List<Wallpaper>> =
        dao.getAll().map { entities -> entities.map(FavouriteEntity::toWallpaper) }
}

// ── Mappers ───────────────────────────────────────────────────────────────────

private fun Wallpaper.toEntity() = FavouriteEntity(
    id = id,
    previewUrl = previewUrl,
    smallUrl = smallUrl,
    wallpaperUrl = wallpaperUrl,
    user = user,
    userImageUrl = userImageUrl,
)

private fun FavouriteEntity.toWallpaper() = Wallpaper(
    id = id,
    previewUrl = previewUrl,
    smallUrl = smallUrl,
    wallpaperUrl = wallpaperUrl,
    user = user,
    userImageUrl = userImageUrl,
)
