package dev.jesusgonzalez.kotlinproject.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Favorites

@Serializable
object Pokemon

@Serializable
data class PokemonDetails(val id: Int)

@Serializable
object Types

@Serializable
object Items

@Serializable
object Movements