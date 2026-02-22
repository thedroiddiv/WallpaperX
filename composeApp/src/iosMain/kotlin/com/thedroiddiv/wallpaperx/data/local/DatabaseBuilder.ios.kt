package com.thedroiddiv.wallpaperx.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun createWallpaperDatabase(): WallpaperDatabase =
    Room.databaseBuilder<WallpaperDatabase>(
        name = NSHomeDirectory() + "/wallpaper.db",
        factory = { WallpaperDatabase::class.instantiateImpl() },
    ).build()
