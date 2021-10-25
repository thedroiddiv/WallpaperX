package com.dxn.wallpaperx.domain.models

import android.os.Parcelable
import java.io.Serializable

data class Wallpaper(
    val id:Int,
    val thumbUrl:String,
    val wallpaperUrl:String
): Serializable