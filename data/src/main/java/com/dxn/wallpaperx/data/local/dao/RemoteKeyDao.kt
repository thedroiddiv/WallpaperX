package com.dxn.wallpaperx.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dxn.wallpaperx.data.local.entities.RemoteKeyEntity

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeyEntity>)

    @Query("Select * From remote_key Where wallpaperId = :id")
    suspend fun getRemoteKeyByWallpaperId(id: String): RemoteKeyEntity?

    @Query("Delete From remote_key")
    suspend fun clear()

    @Query("Select createdAt From remote_key Order By createdAt DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}
