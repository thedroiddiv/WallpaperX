package com.dxn.wallpaperx.data.local.favourites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites_table")
data class FavouriteEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "preview_url") val previewUrl: String,
    @ColumnInfo(name = "small_url") val smallUrl: String,
    @ColumnInfo(name = "wallpaper_url") val wallpaperUrl: String,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "user_image_url") val userImageURL: String,
)