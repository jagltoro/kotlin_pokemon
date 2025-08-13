package dev.jesusgonzalez.kotlinproject.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.jesusgonzalez.kotlinproject.screens.favorites.Favorites
import dev.jesusgonzalez.kotlinproject.screens.home.Home
import dev.jesusgonzalez.kotlinproject.screens.items.Items
import dev.jesusgonzalez.kotlinproject.screens.movements.Movements
import dev.jesusgonzalez.kotlinproject.screens.pokemon.Pokemon
import dev.jesusgonzalez.kotlinproject.screens.pokemon.PokemonDetails
import dev.jesusgonzalez.kotlinproject.screens.types.TypeDetails
import dev.jesusgonzalez.kotlinproject.screens.types.Types

@Composable
fun Navigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = Home,
    modifier = Modifier.background(MaterialTheme.colorScheme.background)
  ) {
    composable<Home> {
      Home(navController = navController)
    }
    composable<Pokemon> {
      Pokemon(navController = navController)
    }
    composable<Types> {
      Types(navController = navController)
    }
    composable<Movements> {
      Movements()
    }
    composable<Favorites> {
      Favorites()
    }
    composable<Items> {
      Items()
    }
    composable<PokemonDetails> { entry ->
      val pokemon: PokemonDetails = entry.toRoute()
      PokemonDetails(id = pokemon.id)
    }
    composable<TypeDetailsRoute> { entry ->
      val details: TypeDetailsRoute = entry.toRoute()
      TypeDetails(id = details.id)
    }
  }
}
