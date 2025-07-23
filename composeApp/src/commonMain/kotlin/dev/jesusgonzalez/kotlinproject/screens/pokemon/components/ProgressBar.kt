package dev.jesusgonzalez.kotlinproject.screens.pokemon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable

fun ProgressBar(
  progress: Float,
  activeColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
  bgColor: Color = MaterialTheme.colorScheme.primaryContainer,
  height: Dp = 15.dp
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(height)
      .clip(RoundedCornerShape(height / 2))
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(height)
        .background(bgColor)
    )
    Box(
      modifier = Modifier
        .fillMaxWidth(progress)
        .height(height)
        .background(activeColor)
    )
  }
}