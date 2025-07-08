package dev.jesusgonzalez.kotlinproject

import androidx.compose.runtime.Composable
import dev.jesusgonzalez.kotlinproject.navigation.Navigation
import dev.jesusgonzalez.kotlinproject.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
  Theme {
    Navigation()
  }
}