package com.thedroiddiv.wallpaperx.data.local

import com.thedroiddiv.wallpaperx.data.model.Wallpaper
import kotlinx.coroutines.flow.Flow

/**
 * Platform-agnostic interface for local favourites storage.
 *
 * Room-backed implementations live in roomMain (Android/iOS/JVM).
 * JS and WasmJS will provide a separate implementation once the
 * custom DB abstraction is in place.
 */
interface LocalDataSource {
    suspend fun addFavourite(wallpaper: Wallpaper)
    suspend fun removeFavourite(id: String)
    fun getFavourites(): Flow<List<Wallpaper>>
}
