package com.dxn.wallpaperx

import android.Manifest.permission
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import android.content.pm.PackageManager

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE

import androidx.core.content.ContextCompat

import android.Manifest.permission.READ_EXTERNAL_STORAGE

import android.os.Environment

import android.os.Build
import android.os.Build.VERSION

import android.os.Build.VERSION.SDK_INT


@HiltAndroidApp
class WallpaperXApp : Application() {}