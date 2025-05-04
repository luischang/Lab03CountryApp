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
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

//Add data countries
data class Country(val name: String, val ranking: Int, val flag: String)

//add mock data
val mockCountries = listOf(
    Country("Argentina", 1, "https://flagcdn.com/w320/ar.png"),
    Country("Brasil", 2, "https://flagcdn.com/w320/br.png"),
    Country("Perú", 43, "https://flagcdn.com/w320/pe.png"),
    Country("Colombia", 5, "https://flagcdn.com/w320/co.png"),
    Country("Chile", 15, "https://flagcdn.com/w320/cl.png"),
    Country("Venezuela", 6, "https://flagcdn.com/w320/ve.png"),
    Country("Uruguay", 7, "https://flagcdn.com/w320/uy.png"),
)

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text("Ranking FIFA", style =  MaterialTheme.typography.titleLarge)

        LazyColumn {
            items(mockCountries){country ->
                //TODO: Add Card and Text with countries
                Card(modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp))
                {
                    Row(modifier = Modifier.padding(12.dp)){
                        Image(
                            painter = rememberAsyncImagePainter(country.flag),
                            contentDescription = country.name,
                            modifier = Modifier.size(64.dp),
                            //contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.padding(end = 16.dp))
                        Column() {
                            Text(text = country.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = country.ranking.toString(), style = MaterialTheme.typography.titleSmall)
                        }

                    }
                }
            }
        }


    }

}