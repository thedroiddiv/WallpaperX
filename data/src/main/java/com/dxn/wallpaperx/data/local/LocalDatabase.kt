package com.dxn.wallpaperx.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dxn.wallpaperx.data.local.dao.FavouriteDao
import com.dxn.wallpaperx.data.local.entities.FavouriteEntity
import com.dxn.wallpaperx.data.local.entities.RemoteKeyEntity
import com.dxn.wallpaperx.data.local.entities.WallpaperEntity

@Database(
    entities = [FavouriteEntity::class, WallpaperEntity::class, RemoteKeyEntity::class],
    version = 2,
    exportSchema = true,
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getNoteDao(): FavouriteDao

    companion object {
        private var dbInstance: LocalDatabase? = null

        fun getNoteDatabase(context: Context): LocalDatabase {
            return dbInstance ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        "local_database",
                    ).build()
                dbInstance = instance
                return instance
            }
        }
    }
}
