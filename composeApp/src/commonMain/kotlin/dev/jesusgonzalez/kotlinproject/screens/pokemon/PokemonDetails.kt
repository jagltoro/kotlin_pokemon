package dev.jesusgonzalez.kotlinproject.screens.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import dev.jesusgonzalez.kotlinproject.networking.PokemonClient
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonDetailsResponse
import dev.jesusgonzalez.kotlinproject.screens.pokemon.components.PokemonStats
import dev.jesusgonzalez.kotlinproject.screens.pokemon.components.PokemonType
import dev.jesusgonzalez.kotlinproject.util.NetworkError
import dev.jesusgonzalez.kotlinproject.util.Result
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.pokemon_icon
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable

fun PokemonDetails(id: Int) {

  var pokemonDetailsResult: Result<PokemonDetailsResponse, NetworkError>? by remember {
    mutableStateOf(
      null
    )
  }
  var isLoading by remember { mutableStateOf(false) }
  val scope = rememberCoroutineScope()
  val clientHttp = remember { PokemonClient() }

  LaunchedEffect(true) {
    isLoading = true
    scope.launch {
      pokemonDetailsResult = clientHttp.getPokemonDetails(id)
      isLoading = false
    }
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

    pokemonDetailsResult is Result.Success -> {
      val pokemonDetails = (pokemonDetailsResult as Result.Success<PokemonDetailsResponse>).data
      Column(
        modifier = Modifier
          .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        AsyncImage(
          model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png",
          contentDescription = pokemonDetails.name,
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .height(300.dp)
            .width(300.dp)
            .offset(y = 50.dp)
            .zIndex(3f),
          error = painterResource(Res.drawable.pokemon_icon),
        )
        Column(
          modifier = Modifier
            .fillMaxSize()
            .offset(y = (-50).dp)
            .clip(
              shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
              )
            )
            .background(MaterialTheme.colorScheme.secondary)
            .padding(top = 80.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = pokemonDetails.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(16.dp)
          )
          PokemonType(pokemonDetails.types)
          PokemonStats(pokemonDetails.stats)
        }
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