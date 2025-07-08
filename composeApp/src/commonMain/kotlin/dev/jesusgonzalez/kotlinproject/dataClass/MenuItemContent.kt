package dev.jesusgonzalez.kotlinproject.dataClass

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class MenuItemContent(
  val title: String,
  val color: Color,
  val icon: DrawableResource,
  val onClick: () -> Unit = {}
)
