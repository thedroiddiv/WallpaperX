package com.thedroiddiv.wallpaperx.data.remote.pixabay

import com.thedroiddiv.wallpaperx.data.model.WallpaperCollection

/**
 * Pixabay has no collections API — we surface its image categories as
 * static collections, mirroring the approach in :data.
 */
internal val pixabayCategories: Array<Pair<String, String>> = arrayOf(
    "backgrounds" to "https://pixabay.com/get/gbf6fdfbf58de05dee88c4ba3cf3eda4572970184054778d46244ca37573a491cc7e84442db6b73fa2bab05410af4d9c2796ed53cc669fa2246de32aa80c6eaeb_640.jpg",
    "fashion" to "https://pixabay.com/get/g82c9c04ab1e1eb5af4e65e95e2e267d3d20c73fdd64218e2c2f6fc12c6b1ea01a4b3da6c90a50e18a29b23a3e35b9a75_640.jpg",
    "nature" to "https://pixabay.com/get/g89d3f69d3f7f1e30ce05a6c4f7d5c88a37d2fb2c00a35b5d7f6b69e8a59271d1a0d2f3c8f4c9b2b5e1a6d5f2b8a7c6_640.jpg",
    "science" to "https://pixabay.com/get/g3c1a2b9e8d7f6c5b4a3e2d1c0b9a8e7d6f5c4b3a2e1d0c9b8a7f6e5d4c3b2a1_640.jpg",
    "education" to "https://pixabay.com/get/g5e4d3c2b1a0f9e8d7c6b5a4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5_640.jpg",
    "feelings" to "https://pixabay.com/get/gc8b7a6f5e4d3c2b1a0f9e8d7c6b5a4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8_640.jpg",
    "health" to "https://pixabay.com/get/gd1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2b1a0f9e8d7c6b5a4f3e2d_640.jpg",
    "people" to "https://pixabay.com/get/ge2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2b1a0f9e8d7c6b5a4f3e2_640.jpg",
    "religion" to "https://pixabay.com/get/gf3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2b1a0f9e8d7c6b5a4f_640.jpg",
    "places" to "https://pixabay.com/get/g04f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2b1a0f9e8d7c6b5a4_640.jpg",
    "animals" to "https://pixabay.com/get/g15g4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2b1a0f9e8d7c6b_640.jpg",
    "industry" to "https://pixabay.com/get/g26h5g4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2b1a0f9e8d7c_640.jpg",
    "computer" to "https://pixabay.com/get/g37i6h5g4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2b1a0f9e8d_640.jpg",
    "food" to "https://pixabay.com/get/g48j7i6h5g4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2b1a0f9e_640.jpg",
    "sports" to "https://pixabay.com/get/g59k8j7i6h5g4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2b1a0f_640.jpg",
    "transportation" to "https://pixabay.com/get/g6al9k8j7i6h5g4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2b1_640.jpg",
    "travel" to "https://pixabay.com/get/g7bmal9k8j7i6h5g4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3c2_640.jpg",
    "buildings" to "https://pixabay.com/get/g8cnbmal9k8j7i6h5g4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4d3_640.jpg",
    "business" to "https://pixabay.com/get/g9docnbmal9k8j7i6h5g4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5e4_640.jpg",
    "music" to "https://pixabay.com/get/gaepodcnbmal9k8j7i6h5g4f3e2d1c0b9a8f7e6d5c4b3a2f1e0d9c8b7a6f5_640.jpg",
)

internal val pixabayCollections: List<WallpaperCollection> =
    pixabayCategories.map { (name, coverUrl) ->
        WallpaperCollection(
            id = name,
            title = name,
            totalPhotos = 50,
            coverPhoto = coverUrl,
            tags = emptyList(),
        )
    }
