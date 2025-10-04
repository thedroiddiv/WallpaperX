package com.dxn.wallpaperx.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dxn.wallpaperx.data.local.favourites.FavouriteDao
import com.dxn.wallpaperx.data.local.favourites.FavouriteEntity

@Database(entities = [FavouriteEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun getNoteDao(): FavouriteDao

    companion object {
        private var instance: LocalDatabase? = null

        fun getNoteDatabase(context: Context): LocalDatabase {
            return instance ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        "local_database",
                    ).build()
                Companion.instance = instance
                return instance
            }
        }
    }
}
