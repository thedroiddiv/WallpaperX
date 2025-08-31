package com.thedroiddiv.wallpaperx

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform