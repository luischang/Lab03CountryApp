package dev.luischang.lab03countryapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import dev.luischang.lab03countryapp.data.model.CountryModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

@Composable
fun CountryList(
    countries: List<CountryModel>,
    favorites: List<String>,
    onToggleFavorite: ((CountryModel) -> Unit)? = null // opcional
) {
    LazyColumn {
        items(countries) { country ->
            val isFavorite = favorites.contains(country.name)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(modifier = Modifier.padding(12.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(country.imageUrl),
                        contentDescription = country.name,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(country.name, style = MaterialTheme.typography.titleMedium)
                        Text("Ranking FIFA: ${country.ranking}")
                    }

                    // Mostrar estrella solo si la función fue pasada
                    onToggleFavorite?.let {
                        IconButton(onClick = { it(country) }) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Star else Icons.Filled.StarBorder,
                                contentDescription = "Favorito"
                            )
                        }
                    }
                }
            }
        }
    }
}