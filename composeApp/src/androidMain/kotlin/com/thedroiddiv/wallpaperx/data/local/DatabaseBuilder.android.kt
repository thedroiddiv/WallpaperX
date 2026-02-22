package com.thedroiddiv.wallpaperx.data.local

import android.content.Context
import androidx.room.Room

fun createWallpaperDatabase(context: Context): WallpaperDatabase =
    Room.databaseBuilder<WallpaperDatabase>(
        context = context.applicationContext,
        name = context.getDatabasePath("wallpaper.db").absolutePath,
    ).build()
