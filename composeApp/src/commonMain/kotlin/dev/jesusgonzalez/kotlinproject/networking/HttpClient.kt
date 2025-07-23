package dev.jesusgonzalez.kotlinproject.networking

import io.ktor.client.*
import io.ktor.client.engine.cio.* // Or your chosen engine
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.* // Optional: for logging requests/responses
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun createHttpClient(): HttpClient {
  return HttpClient(CIO) { // Replace CIO with your chosen engine if different
    // Configure Content Negotiation (e.g., for JSON)
    install(ContentNegotiation) {
      json(Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true // Helpful for API changes
      })
    }

    // Configure Logging (Optional)
    install(Logging) {
      logger = Logger.DEFAULT
      level = LogLevel.ALL // Or LogLevel.INFO, LogLevel.BODY, etc.
    }

    // Other common configurations (timeouts, default headers, etc.)
    // install(HttpTimeout) {
    //     requestTimeoutMillis = 15000
    //     connectTimeoutMillis = 15000
    //     socketTimeoutMillis = 15000
    // }

    // defaultRequest {
    //     header("X-Api-Key", "your_api_key_if_needed")
    // }
  }
}
