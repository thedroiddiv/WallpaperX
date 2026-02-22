package com.thedroiddiv.wallpaperx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteEntity::class], version = 1)
abstract class WallpaperDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
}
