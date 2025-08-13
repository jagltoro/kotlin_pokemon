package dev.jesusgonzalez.kotlinproject.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.pokemon_icon
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
  // Simulate loading/initialization
  LaunchedEffect(Unit) {
    delay(2000) // Display splash for 2 seconds
    onTimeout()
  }

  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.primary // Or your desired background
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Replace "logo.png" with your actual logo file name
      Image(
        painter = painterResource(Res.drawable.pokemon_icon), // Or your specific resource path
        contentDescription = "App Logo",
        modifier = Modifier.size(128.dp)
      )
    }
  }
}