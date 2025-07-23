package dev.jesusgonzalez.kotlinproject.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonSprites(
  val back_default: String,
  val back_female: String?,
  val back_shiny: String,
  val back_shiny_female: String?,
  val front_default: String,
  val front_female: String?,
)