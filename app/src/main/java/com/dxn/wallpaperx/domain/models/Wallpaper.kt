package com.dxn.wallpaperx.domain.models

import java.io.Serializable
data class Wallpaper(
    val id:Int,
    val previewUrl:String,
    val smallUrl:String,
    val wallpaperUrl:String
): Serializable