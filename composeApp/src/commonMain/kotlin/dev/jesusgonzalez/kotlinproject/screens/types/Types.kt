package dev.jesusgonzalez.kotlinproject.screens.types

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
fun Types() {
  Box(
    modifier = Modifier
      .fillMaxSize()
  ) {
    Text(
      text = "TTEMS",
      style = MaterialTheme.typography.titleLarge,
      color = Color(0xFFFFFFFF),
      modifier = Modifier.padding(8.dp)
    )
  }
}