package dev.jesusgonzalez.kotlinproject.screens.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.jesusgonzalez.kotlinproject.networking.PokemonClient
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonListResponse
import dev.jesusgonzalez.kotlinproject.networking.util.NetworkError
import dev.jesusgonzalez.kotlinproject.networking.util.Result
import dev.jesusgonzalez.kotlinproject.screens.pokemon.components.PokemonListRenderer
import dev.jesusgonzalez.kotlinproject.theme.Paddings

@Composable
fun Pokemon(navController: NavController) {
  var pokemonListResult: Result<PokemonListResponse, NetworkError>? by remember {
    mutableStateOf(
      null
    )
  }
  var searchQuery by remember { mutableStateOf("") }
  var isLoading by remember { mutableStateOf(false) }
  val clientHttp = remember { PokemonClient() }

  LaunchedEffect(true) {
    isLoading = true
    pokemonListResult = clientHttp.getPokemonList()
    isLoading = false
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .safeContentPadding()
  ) {
    Box(
      modifier = Modifier.fillMaxWidth().padding(vertical = Paddings.medium)
        .background(MaterialTheme.colorScheme.background),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = "Pokemon",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(Paddings.medium)
      )
    }

    TextField( // Or use TextField for a different style
      value = searchQuery,
      onValueChange = { searchQuery = it },
      label = { Text("Search Pokémon") },
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = Paddings.medium, vertical = Paddings.small)
    )

    when {
      isLoading -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          CircularProgressIndicator()
        }
      }

      pokemonListResult is Result.Success -> {
        val pokemonList = (pokemonListResult as Result.Success<PokemonListResponse>).data.results

        val filteredPokemon = if (searchQuery.isBlank()) {
          pokemonList
        } else {
          pokemonList.filter { pokemon ->
            pokemon.name.contains(searchQuery, ignoreCase = true)
          }
        }

        if (filteredPokemon.isNotEmpty()) {
          PokemonListRenderer(filteredPokemon, navController)
        }
      }

      pokemonListResult is Result.Error -> {
        // Show an error message
        val errorMessage = when ((pokemonListResult as Result.Error<NetworkError>).error) {
          NetworkError.NO_INTERNET -> "No internet connection."
          NetworkError.SERIALIZATION -> "Error parsing data."
          NetworkError.UNKNOWN -> "An unknown error occurred."
          // Add other NetworkError cases as needed
          else -> "Failed to load Pokémon."
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          Text(
            errorMessage, style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondary,
          )
        }
      }

      else -> {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          Text(
            "Loading Pokémon...", style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondary,
          )
        }
      }
    }
  }
}