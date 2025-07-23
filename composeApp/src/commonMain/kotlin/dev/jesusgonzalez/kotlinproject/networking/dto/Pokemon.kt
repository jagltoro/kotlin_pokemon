package dev.jesusgonzalez.kotlinproject.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListItem(
  val name: String,
  val url: String
)

@Serializable
data class PokemonListResponse(
  val count: Int,
  val next: String?,
  val previous: String?,
  val results: List<PokemonListItem>
)

@Serializable
data class PokemonDetailsResponse(
  val id: Int,
  val name: String,
  val height: Int,
  val weight: Int,
  val sprites: PokemonSprites,
  val types: List<PokemonTypes>,
  val stats: List<PokemonStats>
)

@Serializable
data class PokemonStats(
  val base_stat: Int,
  val effort: Int,
  val stat: PokemonStat
)

@Serializable
data class PokemonStat(
  val name: String,
  val url: String
)
