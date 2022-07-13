package com.dxn.wallpaperx.common.helpers

import android.content.Context
import androidx.annotation.StringRes
import com.dxn.wallpaperx.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

    val unsplashApiKey get() = context.getString(R.string.unsplash_api_key)
}