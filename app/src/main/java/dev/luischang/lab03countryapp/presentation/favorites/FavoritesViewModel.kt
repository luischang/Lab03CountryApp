package dev.luischang.lab03countryapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.luischang.lab03countryapp.data.local.FavoriteCountryEntity
import dev.luischang.lab03countryapp.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class FavoritesViewModel(private val repository: FavoriteRepository) : ViewModel() {

   val favorites: StateFlow<List<FavoriteCountryEntity>> =
       repository.getAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),emptyList())

    fun insertFavorite(favoriteCountry: FavoriteCountryEntity) {
        viewModelScope.launch {
            repository.insert(favoriteCountry)
        }
    }
    fun deleteFavorite(favoriteCountry: FavoriteCountryEntity) {
        viewModelScope.launch {
            repository.delete(favoriteCountry)
        }
    }

}