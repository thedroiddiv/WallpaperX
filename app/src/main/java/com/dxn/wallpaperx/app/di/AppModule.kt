package com.dxn.wallpaperx.app.di

import com.dxn.wallpaperx.app.ui.screen.home.HomeScreenVM
import com.dxn.wallpaperx.app.ui.screen.search.SearchScreenVM
import com.dxn.wallpaperx.app.ui.screen.setWallpaper.SetWallpaperScreenVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule =
    module {
        viewModel<HomeScreenVM> { HomeScreenVM(get()) }
        viewModel<SearchScreenVM> { SearchScreenVM(get()) }
        viewModel<SetWallpaperScreenVM> { SetWallpaperScreenVM(get(), get()) }
    }
