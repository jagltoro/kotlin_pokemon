package dev.jesusgonzalez.kotlinproject.screens.types

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TypeDetails(id: Int) {
  Text(
    text = "ID -> $id",
    style = MaterialTheme.typography.titleMedium,
    color = MaterialTheme.colorScheme.onTertiary,
  )
}