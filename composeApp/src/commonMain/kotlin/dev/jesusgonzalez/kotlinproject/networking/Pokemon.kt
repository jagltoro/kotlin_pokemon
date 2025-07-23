package dev.jesusgonzalez.kotlinproject.networking

import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonDetailsResponse
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonEvolutionResponse
import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonListResponse
import dev.jesusgonzalez.kotlinproject.util.NetworkError
import dev.jesusgonzalez.kotlinproject.util.Result
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class PokemonClient() {
  val client = createHttpClient()
  suspend fun getPokemonList(): Result<PokemonListResponse, NetworkError> {
    val response = try {
      client.get("https://pokeapi.co/api/v2/pokemon/?limit=9999") {
        header("Content-Type", "application/json")
      }
    } catch (e: UnresolvedAddressException) {
      return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
      return Result.Error(NetworkError.SERIALIZATION)
    }
    return when (response.status.value) {
      in 200..299 -> {
        val result = response.body<PokemonListResponse>()
        Result.Success(result)
      }

      401 -> Result.Error(NetworkError.UNAUTHORIZED)
      409 -> Result.Error(NetworkError.CONFLICT)
      408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
      in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
      else -> Result.Error(NetworkError.UNKNOWN)
    }
  }

  suspend fun getPokemonDetails(id: Int): Result<PokemonDetailsResponse, NetworkError> {
    val response = try {
      client.get("https://pokeapi.co/api/v2/pokemon/$id") {
        header("Content-Type", "application/json")
      }
    } catch (e: UnresolvedAddressException) {
      return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
      return Result.Error(NetworkError.SERIALIZATION)
    }
    return when (response.status.value) {
      in 200..299 -> {
        val result = response.body<PokemonDetailsResponse>()
        Result.Success(result)
      }

      401 -> Result.Error(NetworkError.UNAUTHORIZED)
      409 -> Result.Error(NetworkError.CONFLICT)
      408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
      in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
      else -> Result.Error(NetworkError.UNKNOWN)
    }
  }

  suspend fun getPokemonEvolutions(id: Int): Result<PokemonEvolutionResponse, NetworkError> {
    val response = try {
      client.get("https://pokeapi.co/api/v2/evolution-chain/$id") {
        header("Content-Type", "application/json")
      }
    } catch (e: UnresolvedAddressException) {
      return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
      return Result.Error(NetworkError.SERIALIZATION)
    }
    return when (response.status.value) {
      in 200..299 -> {
        val result = response.body<PokemonEvolutionResponse>()
        Result.Success(result)
      }

      401 -> Result.Error(NetworkError.UNAUTHORIZED)
      409 -> Result.Error(NetworkError.CONFLICT)
      408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
      in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
      else -> Result.Error(NetworkError.UNKNOWN)
    }
  }
}