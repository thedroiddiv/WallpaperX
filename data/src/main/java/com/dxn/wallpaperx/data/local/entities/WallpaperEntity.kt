package com.dxn.wallpaperx.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallpaper")
class WallpaperEntity(
    @PrimaryKey val id: String,
    val previewUrl: String,
    val smallUrl: String,
    val wallpaperUrl: String,
    val user: String,
    val userImageURL: String,
    val page: Int,
)
