package com.dxn.wallpaperx.domain.usecases

import android.graphics.Bitmap
import com.dxn.wallpaperx.domain.models.SavedWallpaper
import com.dxn.wallpaperx.domain.repositories.WallpaperRepository
import javax.inject.Inject

class GetSavedWallpapers
@Inject
constructor(
    private val repository: WallpaperRepository
) {
    suspend operator fun invoke(): List<SavedWallpaper> {
        return repository.getSavedWallpapers()
    }
}

class SaveWallpaper
@Inject
constructor(private val repository: WallpaperRepository) {
    suspend operator fun invoke(bitmap: Bitmap, displayName: String) =
        repository.saveWallpaper(SavedWallpaper(displayName, bitmap))

}