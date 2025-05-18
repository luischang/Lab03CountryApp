package dev.luischang.lab03countryapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import dev.luischang.lab03countryapp.data.local.AppDatabase
import dev.luischang.lab03countryapp.data.local.FavoriteCountryEntity
import dev.luischang.lab03countryapp.data.model.CountryModel
import dev.luischang.lab03countryapp.data.repository.FavoriteRepository
import dev.luischang.lab03countryapp.presentation.components.CountryList
import dev.luischang.lab03countryapp.presentation.favorites.FavoritesViewModel
import dev.luischang.lab03countryapp.presentation.favorites.FavoritesViewModelFactory


//add mock data
val mockCountries = listOf(
    CountryModel("Argentina", 1, "https://flagcdn.com/w320/ar.png"),
    CountryModel("Brasil", 2, "https://flagcdn.com/w320/br.png"),
    CountryModel("Perú", 43, "https://flagcdn.com/w320/pe.png"),
    CountryModel("Colombia", 5, "https://flagcdn.com/w320/co.png"),
    CountryModel("Chile", 15, "https://flagcdn.com/w320/cl.png"),
    CountryModel("Venezuela", 6, "https://flagcdn.com/w320/ve.png"),
    CountryModel("Uruguay", 7, "https://flagcdn.com/w320/uy.png"),
)

@Composable
fun HomeScreen(){
    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context)}
    val repository = remember { FavoriteRepository(db.favoriteCountryDao()) }
    val viewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModelFactory(repository))

    val favorites by viewModel.favorites.collectAsState()
    val favoritesNames = favorites.map { it.name }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text("Ranking FIFA", style =  MaterialTheme.typography.titleLarge)

        CountryList(
            countries = mockCountries,
            favorites = favoritesNames,
            onToggleFavorite = { country ->
                // Lógica para manejar el cambio de favorito
                val isFavorite = favoritesNames.contains(country.name)
                if (isFavorite) {
                    favorites.find{ it.name == country.name}?.let {
                        viewModel.deleteFavorite(it)
                    }
                } else {
                    viewModel.insertFavorite(
                       FavoriteCountryEntity(
                            name = country.name,
                            ranking = country.ranking,
                            imageUrl = country.imageUrl,
                            countryId = 0
                       )
                    )
                }
            }
        )
    }

}