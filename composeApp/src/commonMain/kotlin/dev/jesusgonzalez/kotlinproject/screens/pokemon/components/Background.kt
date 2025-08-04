package dev.jesusgonzalez.kotlinproject.screens.pokemon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonTypes
import dev.jesusgonzalez.kotlinproject.util.getColorByType

@Composable

fun PokemonColorBg(types: List<PokemonTypes>) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(getColorByType(types[0].type.name))
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.2f)) // Adjust alpha for darkness
    )
  }
}

