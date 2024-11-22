package com.uvg.lab6pokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uvg.lab6pokemon.network.RetrofitClient
import kotlinx.coroutines.launch

@Composable
fun PokemonListScreen(navController: NavHostController) {
    var pokemonList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val response = RetrofitClient.apiService.getPokemonList(100)
            pokemonList = response.results.mapIndexed { index, pokeResult ->
                Pokemon(id = index + 1, name = pokeResult.name.capitalize()) // Asignar un ID basado en la posición
            }
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(pokemonList) { pokemon ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("pokemonDetail/${pokemon.id}/${pokemon.name}")
                    }
            ) {
                Text(text = pokemon.name)
            }
        }
    }
}
