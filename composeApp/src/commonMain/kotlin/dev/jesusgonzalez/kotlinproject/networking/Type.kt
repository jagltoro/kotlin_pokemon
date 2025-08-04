package dev.jesusgonzalez.kotlinproject.networking

import dev.jesusgonzalez.kotlinproject.networking.dto.PokemonTypeDetailsResponse
import dev.jesusgonzalez.kotlinproject.networking.util.NetworkError
import dev.jesusgonzalez.kotlinproject.networking.util.Result
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class PokemonTypeClient() {
  val client = createHttpClient()

  suspend fun getPokemonTypes(id: Int): Result<PokemonTypeDetailsResponse, NetworkError> {
    val response = try {
      client.get("https://pokeapi.co/api/v2/type/$id") {
        header("Content-Type", "application/json")
      }
    } catch (e: UnresolvedAddressException) {
      return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
      return Result.Error(NetworkError.SERIALIZATION)
    }
    return when (response.status.value) {
      in 200..299 -> {
        val result = response.body<PokemonTypeDetailsResponse>()
        Result.Success(result)
      }

      401 -> Result.Error(NetworkError.UNAUTHORIZED)
      409 -> Result.Error(NetworkError.CONFLICT)
      408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
      in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
      else -> Result.Error(NetworkError.UNKNOWN)
    }
  }

  suspend fun getPokemonTypesByUrl(url: String): Result<PokemonTypeDetailsResponse, NetworkError> {
    val response = try {
      client.get(url) {
        header("Content-Type", "application/json")
      }
    } catch (e: UnresolvedAddressException) {
      return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
      return Result.Error(NetworkError.SERIALIZATION)
    }
    return when (response.status.value) {
      in 200..299 -> {
        val result = response.body<PokemonTypeDetailsResponse>()
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