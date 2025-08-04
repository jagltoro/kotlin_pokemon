package dev.jesusgonzalez.kotlinproject.util

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.jesusgonzalez.kotlinproject.theme.Paddings
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

@Composable
fun TypeIcon(name: String) {
  val drawableRes = getDrawableResourceTypeByName(name)
  Icon(
    painter = painterResource(drawableRes),
    contentDescription = name,
    modifier = Modifier.padding(end = Paddings.medium).size(Paddings.xLarge)
  )
}