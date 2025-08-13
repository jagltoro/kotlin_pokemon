package dev.jesusgonzalez.kotlinproject.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonEvolutionResponse(
  val id: Int,
  val chain: EvolutionChain
)

@Serializable
data class EvolutionChain(
  @SerialName("is_baby")
  val isBaby: Boolean,
  val species: Species,
  @SerialName("evolves_to")
  val evolvesTo: List<EvolutionChain>,
  @SerialName("evolution_details")
  val evolutionDetails: List<EvolutionDetails>
)

@Serializable
data class EvolutionDetails(
  @SerialName("min_level")
  val minLevel: Int?
)

@Serializable
data class Species(
  val name: String,
  val url: String
)