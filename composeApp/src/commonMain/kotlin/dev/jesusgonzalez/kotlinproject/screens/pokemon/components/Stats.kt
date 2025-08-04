package dev.jesusgonzalez.kotlinproject.screens.pokemon.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonStats
import dev.jesusgonzalez.kotlinproject.theme.Paddings

@Composable

fun PokemonStats(stats: List<PokemonStats>) {

  stats.forEach { it ->
    Box(
      modifier = Modifier.padding(
        bottom = Paddings.small,
        start = Paddings.medium,
        end = Paddings.medium
      )
    ) {
      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = it.stat.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSecondary,
          modifier = Modifier.fillMaxWidth(0.5f)
        )
        Column(
          horizontalAlignment = Alignment.End
        ) {
          ProgressBar(
            progress = it.baseStat / 255f,
            height = 12.dp
          )
          Text(
            text = "${it.baseStat}/255",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.inverseOnSurface,
          )
        }
      }
    }
  }
}