package dev.jesusgonzalez.kotlinproject.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.jesusgonzalez.kotlinproject.screens.home.components.MenuItem

@Composable
fun Home(navController: NavController) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(8.dp),
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = "What are you looking for",
      style = MaterialTheme.typography.titleLarge,
      color = MaterialTheme.colorScheme.onBackground,
      modifier = Modifier.padding(8.dp)
    )
    MenuItem(navController)
  }
}