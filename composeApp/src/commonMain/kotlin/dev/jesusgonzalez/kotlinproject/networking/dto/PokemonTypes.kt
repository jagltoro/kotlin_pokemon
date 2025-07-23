package dev.jesusgonzalez.kotlinproject.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypes(
  val slot: Int,
  val type: PokemonType
)

@Serializable
data class PokemonType(
  val name: String,
  val url: String
)
