package com.dxn.wallpaperx.domain.models

import android.graphics.Bitmap
import android.os.Parcelable
import java.io.Serializable

data class SavedWallpaper(
    val displayName: String,
    val bitmap: Bitmap
):Serializable