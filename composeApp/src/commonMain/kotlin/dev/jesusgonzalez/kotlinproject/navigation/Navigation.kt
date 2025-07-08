package dev.jesusgonzalez.kotlinproject.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.jesusgonzalez.kotlinproject.screens.favorites.Favorites
import dev.jesusgonzalez.kotlinproject.screens.home.Home
import dev.jesusgonzalez.kotlinproject.screens.items.Items
import dev.jesusgonzalez.kotlinproject.screens.movements.Movements
import dev.jesusgonzalez.kotlinproject.screens.pokemon.Pokemon
import dev.jesusgonzalez.kotlinproject.screens.types.Types

@Composable
fun Navigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = Screens.Home.name,
    modifier = Modifier.background(MaterialTheme.colorScheme.background).safeContentPadding()
  ) {
    composable(route = Screens.Home.name) {
      Home(navController = navController)
    }
    composable(route = Screens.Pokemon.name) {
      Pokemon()
    }
    composable(route = Screens.Types.name) {
      Types()
    }
    composable(route = Screens.Movements.name) {
      Movements()
    }
    composable(route = Screens.Favorites.name) {
      Favorites()
    }
    composable(route = Screens.Items.name) {
      Items()
    }
  }
}
