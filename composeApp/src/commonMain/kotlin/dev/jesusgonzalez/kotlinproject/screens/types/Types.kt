package dev.jesusgonzalez.kotlinproject.screens.types

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.jesusgonzalez.kotlinproject.navigation.TypeDetailsRoute
import dev.jesusgonzalez.kotlinproject.networking.PokemonTypeClient
import dev.jesusgonzalez.kotlinproject.networking.dto.TypesResponse
import dev.jesusgonzalez.kotlinproject.networking.util.NetworkError
import dev.jesusgonzalez.kotlinproject.networking.util.Result
import dev.jesusgonzalez.kotlinproject.theme.Paddings
import dev.jesusgonzalez.kotlinproject.util.TypeIcon
import dev.jesusgonzalez.kotlinproject.util.getIdFromUrl

@Composable
fun Types(navController: NavController) {

  var typesListResult: Result<TypesResponse, NetworkError>? by remember {
    mutableStateOf(
      null
    )
  }
  var isLoading by remember { mutableStateOf(false) }
  val clientHttp = remember { PokemonTypeClient() }

  LaunchedEffect(true) {
    isLoading = true
    typesListResult = clientHttp.getPokemonTypes()
    isLoading = false
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .safeContentPadding()
  ) {
    Box(
      modifier = Modifier.fillMaxWidth().padding(vertical = Paddings.medium)
        .background(MaterialTheme.colorScheme.background),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = "Types",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(Paddings.medium)
      )
    }
    when {
      isLoading -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          CircularProgressIndicator()
        }
      }

      typesListResult is Result.Success -> {
        val typesList = (typesListResult as Result.Success<TypesResponse>).data.results

        LazyVerticalGrid(
          columns = GridCells.Fixed(3),
          modifier = Modifier.padding(top = Paddings.large)
        ) {
          items(typesList.size) { typeIndex ->
            val typeId = getIdFromUrl(typesList[typeIndex].url).toString()

            Column(
              modifier = Modifier
                .padding(8.dp)
                .clip(
                  RoundedCornerShape(Paddings.medium)
                )
                .background(MaterialTheme.colorScheme.secondary)
                .padding(Paddings.medium)
                .clickable(
                  onClick = {
                    navController.navigate(TypeDetailsRoute(typeId.toInt()))
                  }
                ),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              TypeIcon(typesList[typeIndex].name)
              Text(
                text = typesList[typeIndex].name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiary,
              )
            }
          }
        }
      }
    }
  }
}