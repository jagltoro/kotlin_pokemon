package dev.jesusgonzalez.kotlinproject.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NamedAPIResource(
  val name: String,
  val url: String
)

@Serializable
data class PokemonTypeDetailsResponse(
  val name: String,
  @SerialName("damage_relations")
  val damageRelations: DamageRelations,
  val pokemon: List<PokemonListFromType>
)

@Serializable
data class DamageRelations(
  @SerialName("double_damage_from")
  val doubleDamageFrom: List<NamedAPIResource>,
  @SerialName("double_damage_to")
  val doubleDamageTo: List<NamedAPIResource>,
  @SerialName("half_damage_from")
  val halfDamageFrom: List<NamedAPIResource>,
  @SerialName("half_damage_to")
  val halfDamageTo: List<NamedAPIResource>,
  @SerialName("no_damage_from")
  val noDamageFrom: List<NamedAPIResource>,
  @SerialName("no_damage_to")
  val noDamageTo: List<NamedAPIResource>
)

@Serializable
data class PokemonListFromType(
  val pokemon: PokemonListItem,
  val slot: Int
)