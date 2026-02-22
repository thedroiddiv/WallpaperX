package com.thedroiddiv.wallpaperx.data.local

import androidx.room.Room

fun createWallpaperDatabase(): WallpaperDatabase =
    Room.databaseBuilder<WallpaperDatabase>(
        name = System.getProperty("user.home") + "/wallpaper.db",
    ).build()
