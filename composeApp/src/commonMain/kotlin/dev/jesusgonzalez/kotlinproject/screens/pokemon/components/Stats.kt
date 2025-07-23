package dev.jesusgonzalez.kotlinproject.screens.pokemon.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonStats

@Composable

fun PokemonStats(stats: List<PokemonStats>) {
  LazyColumn(
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(stats.size) { statItem ->
      Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = stats[statItem].stat.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.onSecondary,
          modifier = Modifier.fillMaxWidth(0.5f)
        )
        ProgressBar(
          progress = stats[statItem].base_stat / 100f,
          height = 10.dp
        )
      }
    }
  }
}