package dev.jesusgonzalez.kotlinproject.screens.pokemon.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.bug
import kotlinproject.composeapp.generated.resources.dark
import kotlinproject.composeapp.generated.resources.dragon
import kotlinproject.composeapp.generated.resources.electric
import kotlinproject.composeapp.generated.resources.fairy
import kotlinproject.composeapp.generated.resources.fighting
import kotlinproject.composeapp.generated.resources.fire
import kotlinproject.composeapp.generated.resources.flying
import kotlinproject.composeapp.generated.resources.ghost
import kotlinproject.composeapp.generated.resources.grass
import kotlinproject.composeapp.generated.resources.ground
import kotlinproject.composeapp.generated.resources.ice
import kotlinproject.composeapp.generated.resources.normal
import kotlinproject.composeapp.generated.resources.poison
import kotlinproject.composeapp.generated.resources.pokeball
import kotlinproject.composeapp.generated.resources.psychic
import kotlinproject.composeapp.generated.resources.rock
import kotlinproject.composeapp.generated.resources.steel
import kotlinproject.composeapp.generated.resources.water
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PokemonType(types: List<PokemonTypes>) {
  LazyRow(
    modifier = Modifier.padding(vertical = 8.dp),
    contentPadding = PaddingValues(horizontal = 16.dp)
  ) {
    items(types.size) { typeItem ->
      Column(
        modifier = Modifier
          .padding(horizontal = 8.dp)
          .clip(RoundedCornerShape(4.dp))
          .background(MaterialTheme.colorScheme.tertiary)
          .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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

@Composable
fun TypeIcon(name: String) {
  val drawableRes = getDrawableResourceTypeByName(name)
  Image(
    painter = painterResource(drawableRes),
    contentDescription = name,
  )
}

fun getDrawableResourceTypeByName(name: String): DrawableResource {
  return when (name.lowercase()) {
    "bug" -> Res.drawable.bug
    "dark" -> Res.drawable.dark
    "dragon" -> Res.drawable.dragon
    "electric" -> Res.drawable.electric
    "fairy" -> Res.drawable.fairy
    "fighting" -> Res.drawable.fighting
    "fire" -> Res.drawable.fire
    "flying" -> Res.drawable.flying
    "ghost" -> Res.drawable.ghost
    "grass" -> Res.drawable.grass
    "ground" -> Res.drawable.ground
    "ice" -> Res.drawable.ice
    "normal" -> Res.drawable.normal
    "poison" -> Res.drawable.poison
    "psychic" -> Res.drawable.psychic
    "rock" -> Res.drawable.rock
    "steel" -> Res.drawable.steel
    "water" -> Res.drawable.water
    else -> Res.drawable.pokeball // Default or handle error
  }
}