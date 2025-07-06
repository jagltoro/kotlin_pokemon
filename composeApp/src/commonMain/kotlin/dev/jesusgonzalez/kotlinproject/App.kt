package dev.jesusgonzalez.kotlinproject

import androidx.compose.runtime.*
import dev.jesusgonzalez.kotlinproject.screens.home.Home
import dev.jesusgonzalez.kotlinproject.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    Theme {
        Home()
    }
}