package com.dxn.wallpaperx.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_wallpapers")
data class Wallpaper (
    @PrimaryKey val id:Int,
    @ColumnInfo(name = "preview_url") val previewUrl:String,
    @ColumnInfo(name = "small_url") val smallUrl:String,
    @ColumnInfo(name = "wallpaper_url") val wallpaperUrl:String
)