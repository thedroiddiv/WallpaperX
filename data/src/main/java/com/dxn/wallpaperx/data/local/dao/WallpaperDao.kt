package com.dxn.wallpaperx.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dxn.wallpaperx.data.local.entities.WallpaperEntity

@Dao
interface WallpaperDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<WallpaperEntity>)

    @Query("Select * From wallpaper Order By page")
    fun getAll(): PagingSource<Int, WallpaperEntity>

    @Query("Delete From wallpaper")
    suspend fun clear()
}
