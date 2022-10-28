package com.dxn.wallpaperx.presentation.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

object Padding {
    val extraSmall = 4.dp
    val small = 8.dp
    val medium = 16.dp
    val large = 24.dp
    val extraLarge = 32.dp

    fun gridItemPadding(index: Int) = if (index % 2 != 0) PaddingValues(
        start = small,
        end = medium,
        bottom = medium
    ) else PaddingValues(
        start = medium,
        bottom = medium,
        end = small
    )
}
