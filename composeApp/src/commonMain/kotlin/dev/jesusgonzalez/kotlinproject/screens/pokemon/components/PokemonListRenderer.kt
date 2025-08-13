package dev.jesusgonzalez.kotlinproject.screens.pokemon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonListItem
import dev.jesusgonzalez.kotlinproject.theme.Paddings
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.pokemon_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun PokemonListRenderer(pokemonList: List<PokemonListItem>, navController: NavController) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = Modifier.padding(top = Paddings.large)
  ) {
    items(pokemonList.size) { pokemonItem ->
      val pokemonId = remember(pokemonList[pokemonItem].url) {
        pokemonList[pokemonItem].url.trimEnd('/').substringAfterLast('/').toInt()
      }

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
              navController.navigate(PokemonDetails(pokemonId))
            }
          ),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = buildAnnotatedString {
            withStyle(
              style = MaterialTheme.typography.bodySmall.toSpanStyle()
            ) {
              append("#${pokemonId}")
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
            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonId}.png",
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