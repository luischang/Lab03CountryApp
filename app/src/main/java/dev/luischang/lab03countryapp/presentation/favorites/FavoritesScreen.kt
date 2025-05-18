package dev.luischang.lab03countryapp.presentation.favorites


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.luischang.lab03countryapp.data.local.AppDatabase
import dev.luischang.lab03countryapp.data.model.CountryModel
import dev.luischang.lab03countryapp.data.repository.FavoriteRepository
import dev.luischang.lab03countryapp.presentation.components.CountryList

@Composable
fun FavoritesScreen(){
    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context)}
    val repository = remember { FavoriteRepository(db.favoriteCountryDao()) }
    val viewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModelFactory(repository))

    val favorites by viewModel.favorites.collectAsState()

    val mappedCountries = favorites.map {
        CountryModel(it.name, it.ranking, it.imageUrl)
    }

    Column(
       modifier = Modifier.fillMaxSize().padding(16.dp)
    ){
        Text("Mis paises favoritos", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.padding(10.dp))
        CountryList(
            countries = mappedCountries,
            favorites = mappedCountries.map{it.name})
    }
}