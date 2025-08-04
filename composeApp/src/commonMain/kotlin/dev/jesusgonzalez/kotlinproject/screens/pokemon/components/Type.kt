package dev.jesusgonzalez.kotlinproject.screens.pokemon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonTypes
import dev.jesusgonzalez.kotlinproject.theme.Paddings
import dev.jesusgonzalez.kotlinproject.util.TypeIcon
import dev.jesusgonzalez.kotlinproject.util.getColorByType

@Composable
fun PokemonType(types: List<PokemonTypes>) {
  LazyRow(
    modifier = Modifier.padding(vertical = 16.dp),
    contentPadding = PaddingValues(horizontal = Paddings.large)
  ) {
    items(types.size) { typeItem ->
      Row(
        modifier = Modifier
          .padding(horizontal = Paddings.medium)
          .clip(RoundedCornerShape(Paddings.small))
          .background(getColorByType(types[typeItem].type.name))
          .padding(Paddings.medium),
        verticalAlignment = Alignment.CenterVertically
      ) {
        TypeIcon(types[typeItem].type.name)
        Text(
          text = types[typeItem].type.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.onTertiary,
        )
      }
    }
  }
}