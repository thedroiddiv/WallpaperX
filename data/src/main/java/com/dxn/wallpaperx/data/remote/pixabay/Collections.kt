package com.dxn.wallpaperx.data.remote.pixabay

import com.dxn.wallpaperx.data.model.Collection

val collections =
    PixabayApi.categories.map {
        Collection(
            id = it.first,
            title = it.first,
            totalPhotos = 50,
            coverPhoto = it.second,
            tags = listOf(),
        )
    }
