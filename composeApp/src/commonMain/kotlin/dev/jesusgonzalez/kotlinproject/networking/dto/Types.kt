package dev.jesusgonzalez.kotlinproject.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class TypesResponse(
  val results: List<Result>
)

@Serializable
data class Result(
  val name: String,
  val url: String
)