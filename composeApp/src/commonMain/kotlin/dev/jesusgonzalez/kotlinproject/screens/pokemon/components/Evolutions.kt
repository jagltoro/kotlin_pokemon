package dev.jesusgonzalez.kotlinproject.screens.pokemon.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import dev.jesusgonzalez.kotlinproject.networking.EvolutionClient
import dev.jesusgonzalez.kotlinproject.networking.SpeciesClient
import dev.jesusgonzalez.kotlinproject.networking.dto.EvolutionChain
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonEvolutionResponse
import dev.jesusgonzalez.kotlinproject.networking.util.NetworkError
import dev.jesusgonzalez.kotlinproject.networking.util.Result
import dev.jesusgonzalez.kotlinproject.theme.Paddings
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.evolutionArrow
import kotlinproject.composeapp.generated.resources.pokemon_icon
import org.jetbrains.compose.resources.painterResource

@Composable

fun PokemonEvolutions(url: String) {

  var pokemonEvolutionResult: Result<PokemonEvolutionResponse, NetworkError>? by remember {
    mutableStateOf(
      null
    )
  }
  var isLoading by remember { mutableStateOf(false) }
  val speciesClientHttp = remember { SpeciesClient() }
  val evolutionClientHttp = remember { EvolutionClient() }

  LaunchedEffect(true) {
    isLoading = true
    val pokemonSpeciesResult = speciesClientHttp.getPokemonSpeciesUrl(url)
    when {
      pokemonSpeciesResult is Result.Success -> {
        val pokemonSpecies = (pokemonSpeciesResult).data
        pokemonEvolutionResult =
          evolutionClientHttp.getPokemonEvolutionsUrl(pokemonSpecies.evolutionChain.url)
      }
    }
    isLoading = false
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(Paddings.medium),
  ) {
    Text(
      text = "Evolutions",
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.onSecondary,
      modifier = Modifier.padding(Paddings.small),
    )
  }

  FlowRow(
    modifier = Modifier
      .padding(Paddings.medium),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    when {
      isLoading -> {
        Box(
          modifier = Modifier
            .fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          CircularProgressIndicator()
        }
      }

      pokemonEvolutionResult is Result.Success -> {
        val pokemonEvolution =
          (pokemonEvolutionResult as Result.Success<PokemonEvolutionResponse>).data

        PokemonImageAndText(pokemonEvolution.chain.species.name, pokemonEvolution.chain.species.url)
        if (pokemonEvolution.chain.evolvesTo.isNotEmpty()) {
          Icon(
            painter = painterResource(Res.drawable.evolutionArrow),
            modifier = Modifier.padding(Paddings.medium).offset(y = 40.dp),
            contentDescription = null,
          )
          EvolvesToCycle(pokemonEvolution.chain.evolvesTo)
        }
      }
    }
  }
}


@Composable
fun EvolvesToCycle(chain: List<EvolutionChain>) {
  chain.forEach {
    PokemonImageAndText(it.species.name, it.species.url)
    if (it.evolvesTo.isNotEmpty()) {
      Icon(
        painter = painterResource(Res.drawable.evolutionArrow),
        modifier = Modifier.padding(Paddings.medium).offset(y = 40.dp),
        contentDescription = null,
      )
      EvolvesToCycle(it.evolvesTo)
    }
  }
}

@Composable
fun PokemonImageAndText(name: String, url: String) {
  val pokemonId = remember(url) {
    url.trimEnd('/').substringAfterLast('/').toIntOrNull()
  }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    if (pokemonId != null) {
      val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png"
      AsyncImage(
        model = imageUrl,
        contentDescription = name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .height(100.dp)
          .width(100.dp)
          .zIndex(3f),
        error = painterResource(Res.drawable.pokemon_icon),
      )
    }
    Text(
      text = name,
      style = MaterialTheme.typography.bodyLarge,
      color = MaterialTheme.colorScheme.onSecondary,
      modifier = Modifier.padding(Paddings.small)
    )
  }
}