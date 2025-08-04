package dev.jesusgonzalez.kotlinproject.screens.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import dev.jesusgonzalez.kotlinproject.navigation.PokemonDetails
import dev.jesusgonzalez.kotlinproject.networking.PokemonClient
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonListResponse
import dev.jesusgonzalez.kotlinproject.networking.util.NetworkError
import dev.jesusgonzalez.kotlinproject.networking.util.Result
import dev.jesusgonzalez.kotlinproject.theme.Paddings
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.pokemon_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun Pokemon(navController: NavController) {
  var pokemonListResult: Result<PokemonListResponse, NetworkError>? by remember {
    mutableStateOf(
      null
    )
  }
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
  ) {
    Box(
      modifier = Modifier.fillMaxWidth().padding(vertical = Paddings.medium)
        .background(MaterialTheme.colorScheme.background),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = "POKEMON",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(Paddings.medium)
      )
    }

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
        if (pokemonList.isNotEmpty()) {
          LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(top = Paddings.large)
          ) {
            items(pokemonList.size) { pokemonItem ->
              Column(
                modifier = Modifier
                  .padding(8.dp)
                  .clip(
                    RoundedCornerShape(Paddings.medium)
                  )
                  .background(MaterialTheme.colorScheme.secondary)
                  .padding(Paddings.medium)
                  .clickable(
                    onClick = {
                      navController.navigate(PokemonDetails(pokemonItem + 1))
                    }
                  ),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Text(
                  text = buildAnnotatedString {
                    withStyle(
                      style = MaterialTheme.typography.bodySmall.toSpanStyle()
                    ) {
                      append("#${pokemonItem + 1}")
                    }
                    append(" ${pokemonList[pokemonItem].name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }}")
                  },
                  style = MaterialTheme.typography.bodyLarge,
                  color = MaterialTheme.colorScheme.onSecondary,
                )
                Box(
                  modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(top = Paddings.medium)
                ) {
                  Box(
                    modifier = Modifier
                      .size(150.dp)
                      .clip(CircleShape) // Apply clipping with rounded corners
                      .background(MaterialTheme.colorScheme.primary)
                      .align(Alignment.Center)
                  )
                  AsyncImage(
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonItem + 1}.png",
                    contentDescription = pokemonList[pokemonItem].name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                      .fillMaxWidth(),
                    error = painterResource(Res.drawable.pokemon_icon),
                  )
                }
              }
            }
          }
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