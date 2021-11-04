package com.dxn.wallpaperx.common.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.Toast
import coil.ImageLoader
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception


fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

suspend fun Context.getBitmap(url: String): Bitmap {
    return withContext(Dispatchers.IO) {
        val loader = ImageLoader(this@getBitmap)
        val request = ImageRequest.Builder(this@getBitmap)
            .data(url)
            .allowHardware(false) // Disable hardware bitmaps.
            .build()
        val res = loader.execute(request)
        when (res) {
            is SuccessResult -> {
                val result = res.drawable
                (result as BitmapDrawable).bitmap
            }
            else -> {
                throw (res as ErrorResult).throwable
            }
        }
    }
}