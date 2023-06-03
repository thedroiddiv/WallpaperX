package com.dxn.wallpaperx.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.dxn.wallpaperx.data.model.Wallpaper
import com.dxn.wallpaperx.domain.repository.WallpaperRepository
import com.dxn.wallpaperx.domain.source.CollectionSource
import com.dxn.wallpaperx.domain.source.WallpaperSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM
@Inject
constructor(
    private val repository: WallpaperRepository

) : ViewModel() {

    init {
        loadFavourites()
    }

    val wallpapers = Pager(PagingConfig(pageSize = 20)) {
        WallpaperSource(repository, "wallpaper", false)
    }.flow.cachedIn(viewModelScope)

    val collections = Pager(PagingConfig(pageSize = 20)) {
        CollectionSource(repository)
    }.flow.cachedIn(viewModelScope)

    private val _favourites = MutableStateFlow(listOf<Wallpaper>())
    val favourites = _favourites.asStateFlow()
    private fun loadFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavourites().collect { favs ->
                _favourites.update { favs }
            }
        }
    }

    fun addFavourite(wallpaper: Wallpaper) {
        launchCatching { repository.addFavourite(wallpaper) }
    }

    fun removeFavourite(id: String) {
        launchCatching { repository.removeFavourite(id) }
    }

    private fun launchCatching(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ) {
        try {
            viewModelScope.launch(dispatcher) {
                block()
            }
        } catch (e: HttpException) {
            // Network call error
            Timber.e(e)
        } catch (e: IOException) {
            // Room db io exception
            Timber.e(e)
        }
    }
}
