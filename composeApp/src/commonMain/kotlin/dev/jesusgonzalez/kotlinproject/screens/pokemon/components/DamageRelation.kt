package dev.jesusgonzalez.kotlinproject.screens.pokemon.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import dev.jesusgonzalez.kotlinproject.networking.PokemonTypeClient
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonTypeDetailsResponse
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonTypes
import dev.jesusgonzalez.kotlinproject.networking.util.Result
import dev.jesusgonzalez.kotlinproject.theme.Paddings
import dev.jesusgonzalez.kotlinproject.util.IconWithText
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

@Composable
fun DamageRelation(types: List<PokemonTypes>) {
  var isLoading by remember { mutableStateOf(false) }
  var error by remember { mutableStateOf<String?>(null) }
  val clientHttp = remember { PokemonTypeClient() }
  val successfulDetails = remember { mutableListOf<PokemonTypeDetailsResponse>() }
  val strengths = remember { mutableSetOf<String>() }
  val weaknesses = remember { mutableSetOf<String>() }

  LaunchedEffect(types) { // Rerun if the Pokemon's types change
    isLoading = true
    error = null

    try {
      val typeDetailsResponses = coroutineScope {
        types.map { pokemonType ->
          async { // Launch each network call concurrently
            clientHttp.getPokemonTypesByUrl(pokemonType.type.url)
          }
        }.awaitAll() // Wait for all calls to complete
      }

      typeDetailsResponses.forEach { result ->
        when (result) {
          is Result.Success -> successfulDetails.add(result.data)
          is Result.Error -> {
            // Handle individual errors, maybe collect them or fail fast
            error = "Error fetching type details: ${result.error.name}"
            isLoading = false
            return@LaunchedEffect // Or handle more gracefully
          }
        }
      }

      if (successfulDetails.isNotEmpty()) {
        successfulDetails.forEach { detail ->
          detail.damageRelations.doubleDamageFrom.forEach { damageFrom ->
            weaknesses.add(damageFrom.name)
          }
          detail.damageRelations.doubleDamageTo.forEach { damageTo ->
            strengths.add(damageTo.name)
          }
        }
      }
    } catch (e: Exception) { // Catch any other exceptions during the process
      error = "An unexpected error occurred: ${e.message}"
    } finally {
      isLoading = false
    }
  }

  when {
    isLoading -> {
      CircularProgressIndicator()
    }

    successfulDetails.isNotEmpty() -> {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(Paddings.medium),
        horizontalAlignment = Alignment.Start
      ) {
        RenderDamageRelation("Strengths", strengths.toList())
        RenderDamageRelation("Weaknesses", weaknesses.toList())
      }
    }

    error != null -> {
      Text(
        text = error!!,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.error,
      )
    }

    successfulDetails.isEmpty() -> {
      Text(
        text = "No data available",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.error,
      )
    }
  }
}


@Composable
fun RenderDamageRelation(title: String, relations: List<String>) {
  if (relations.isNotEmpty()) {
    Column {
      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onTertiary,
      )
      FlowRow(
        modifier = Modifier
          .padding(Paddings.medium),
        verticalArrangement = Arrangement.spacedBy(Paddings.medium)
      ) {
        relations.forEach { relation ->
          IconWithText(relation)
        }
      }
    }
  }
}