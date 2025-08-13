package dev.jesusgonzalez.kotlinproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.jesusgonzalez.kotlinproject.navigation.Navigation
import dev.jesusgonzalez.kotlinproject.screens.splash.SplashScreen
import dev.jesusgonzalez.kotlinproject.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
  Theme {
    var showSplashScreen by remember { mutableStateOf(true) }
    if (showSplashScreen) {
      SplashScreen(onTimeout = { showSplashScreen = false })
    } else {
      Navigation() // Your main application content
    }
  }
}