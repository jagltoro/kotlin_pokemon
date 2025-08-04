package dev.jesusgonzalez.kotlinproject.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSprites(
  @SerialName("back_default")
  val backDefault: String,
  @SerialName("back_female")
  val backFemale: String?,
  @SerialName("back_shiny")
  val backShiny: String,
  @SerialName("back_shiny_female")
  val backShinyFemale: String?,
  @SerialName("front_default")
  val frontDefault: String,
  @SerialName("front_female")
  val frontFemale: String?,
)