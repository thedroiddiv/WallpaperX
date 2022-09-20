package com.dxn.wallpaperx.data.remote.unsplash.models.collection

import com.google.gson.annotations.SerializedName

data class TopicSubmissionsX(
    val animals: Animals,
    @SerializedName("textures-patterns")
    val texturesPatterns: TexturesPatterns
)
