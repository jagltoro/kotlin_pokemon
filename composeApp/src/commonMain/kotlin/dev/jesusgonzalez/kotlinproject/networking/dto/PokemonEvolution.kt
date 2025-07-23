package dev.jesusgonzalez.kotlinproject.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonEvolutionResponse(
  val id: Int,
  val chain: EvolutionChain
)

@Serializable
data class EvolutionChain(
  val is_baby: Boolean,
  val species: Species,
  val evolves_to: List<EvolutionChain>,
  val evolution_details: List<EvolutionDetails>
)

@Serializable
data class EvolutionDetails(
  val min_level: Int
)

@Serializable
data class Species(
  val name: String,
  val url: String
)