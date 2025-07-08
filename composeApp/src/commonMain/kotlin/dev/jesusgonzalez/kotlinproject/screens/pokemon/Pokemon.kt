package dev.jesusgonzalez.kotlinproject.screens.pokemon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Pokemon() {
  Box(
    modifier = Modifier
      .fillMaxSize()
  ) {
    Text(
      text = "POKEMON",
      style = MaterialTheme.typography.titleLarge,
      color = Color(0xFFFFFFFF),
      modifier = Modifier.padding(8.dp)
    )
  }
}