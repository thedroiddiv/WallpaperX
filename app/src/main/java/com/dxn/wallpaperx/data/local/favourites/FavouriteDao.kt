package com.dxn.wallpaperx.data.local.favourites

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(id: FavouriteEntity)

    @Query("DELETE  FROM favourites_table WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM favourites_table")
    fun getAll(): Flow<List<FavouriteEntity>>

}