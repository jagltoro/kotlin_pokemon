package dev.jesusgonzalez.kotlinproject.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object PokemonColors {
  val normal = Color(0xFF9099a1)
  val fire = Color(0xFFff9c54)
  val water = Color(0xFF0c80cf)
  val electric = Color(0xFF0a6dc4)
  val grass = Color(0xFF63bb5b)
  val ice = Color(0xFF74cec0)
  val fighting = Color(0xFFce4069)
  val poison = Color(0xFFab6ac8)
  val ground = Color(0xFFd97746)
  val flying = Color(0xFF66a7cc)
  val psychic = Color(0xFFe4707b)
  val bug = Color(0xFF90c12c)
  val rock = Color(0xFFc7b78b)
  val ghost = Color(0xFF5269ac)
  val dragon = Color(0xFF0a6dc4)
  val dark = Color(0xFF5a5366)
  val steel = Color(0xFF5a8ea1)
  val fairy = Color(0xFFec8fe6)
}

@Composable
fun getColorByType(type: String): Color {
  return when (type) {
    "bug" -> PokemonColors.bug
    "dark" -> PokemonColors.dark
    "dragon" -> PokemonColors.dragon
    "electric" -> PokemonColors.electric
    "fairy" -> PokemonColors.fairy
    "fighting" -> PokemonColors.fighting
    "fire" -> PokemonColors.fire
    "flying" -> PokemonColors.flying
    "ghost" -> PokemonColors.ghost
    "grass" -> PokemonColors.grass
    "ground" -> PokemonColors.ground
    "ice" -> PokemonColors.ice
    "normal" -> PokemonColors.normal
    "poison" -> PokemonColors.poison
    "psychic" -> PokemonColors.psychic
    "rock" -> PokemonColors.rock
    "steel" -> PokemonColors.steel
    "water" -> PokemonColors.water
    else -> MaterialTheme.colorScheme.primary
  }
}