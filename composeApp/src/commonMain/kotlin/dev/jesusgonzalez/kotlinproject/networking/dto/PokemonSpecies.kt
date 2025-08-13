package dev.jesusgonzalez.kotlinproject.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesResponse(
  @SerialName("evolution_chain")
  val evolutionChain: EvolutionChainUrl
)

@Serializable
data class EvolutionChainUrl(
  val url: String
)