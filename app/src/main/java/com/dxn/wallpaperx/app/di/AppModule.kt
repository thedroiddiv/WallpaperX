package com.dxn.wallpaperx.app.di

import com.dxn.wallpaperx.app.ui.screen.home.HomeScreenVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeScreenVM(get()) }
}
