package com.dxn.wallpaperx.data.local.dao

import androidx.room.*
import com.dxn.wallpaperx.data.local.entities.FavouriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favourite: FavouriteEntity)

    @Query("DELETE  FROM favourites_table WHERE id = :id")
    fun delete(id: String): Void

    @Query("SELECT * FROM favourites_table")
    fun getAll(): Flow<List<FavouriteEntity>>
}
