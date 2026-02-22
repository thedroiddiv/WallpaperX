package com.thedroiddiv.wallpaperx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favourite: FavouriteEntity)

    @Query("DELETE FROM favourites WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM favourites")
    fun getAll(): Flow<List<FavouriteEntity>>
}
