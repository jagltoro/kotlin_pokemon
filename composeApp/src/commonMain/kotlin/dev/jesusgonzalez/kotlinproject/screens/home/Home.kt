package dev.jesusgonzalez.kotlinproject.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.jesusgonzalez.kotlinproject.screens.home.components.MenuItem

@Composable
fun Home(){
  Column(modifier = Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background)
    .safeContentPadding()
    .padding(8.dp)
  ){
    Text(
      text = "What are you looking for",
      style = MaterialTheme.typography.titleLarge,
      color = Color(0xFFFFFFFF),
      modifier = Modifier.padding(8.dp)
    )
    MenuItem()
  }
}