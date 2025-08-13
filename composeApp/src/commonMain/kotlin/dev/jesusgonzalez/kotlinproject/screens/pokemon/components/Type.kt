package dev.jesusgonzalez.kotlinproject.screens.pokemon.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonTypes
import dev.jesusgonzalez.kotlinproject.theme.Paddings
import dev.jesusgonzalez.kotlinproject.util.IconWithText

@Composable
fun PokemonType(types: List<PokemonTypes>) {
  LazyRow(
    modifier = Modifier.padding(vertical = 16.dp),
    contentPadding = PaddingValues(horizontal = Paddings.large)
  ) {
    items(types.size) { typeItem ->
      IconWithText(types[typeItem].type.name)
    }
  }
}