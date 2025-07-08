package dev.jesusgonzalez.kotlinproject.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.jesusgonzalez.kotlinproject.dataClass.MenuItemContent
import dev.jesusgonzalez.kotlinproject.navigation.Screens
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.backpack
import kotlinproject.composeapp.generated.resources.fight
import kotlinproject.composeapp.generated.resources.heart
import kotlinproject.composeapp.generated.resources.pokeball
import kotlinproject.composeapp.generated.resources.thunder
import org.jetbrains.compose.resources.painterResource

@Composable
fun MenuItem(navController: NavController) {
  val menuItems = listOf<MenuItemContent>(
    MenuItemContent(
      "Items",
      Color(0xFFF7776A),
      Res.drawable.backpack,
      onClick = { navController.navigate(Screens.Items.name) }),
    MenuItemContent(
      "Moves",
      Color(0xFF58A9F4),
      Res.drawable.fight,
      onClick = { navController.navigate(Screens.Movements.name) }),
    MenuItemContent(
      "Types",
      Color(0xFFFFCE4B),
      Res.drawable.thunder,
      onClick = { navController.navigate(Screens.Types.name) }),
    MenuItemContent(
      "Favorite",
      Color(0xFFB963D0),
      Res.drawable.heart,
      onClick = { navController.navigate(Screens.Favorites.name) }),
  )
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = Modifier.padding(top = 16.dp),
  ) {
    item(span = { GridItemSpan(maxLineSpan) }) {  // let item span across all columns in Grid
      Box(
        modifier = Modifier
          .padding(end = 8.dp, start = 8.dp, bottom = 16.dp)
          .fillMaxWidth()
          .height(120.dp)
          .clip(RoundedCornerShape(16.dp))
          .background(Color(0xFF5DBE62))
          .clickable(enabled = true, onClick = {
            navController.navigate(Screens.Pokemon.name)
          }),
        contentAlignment = Alignment.BottomStart
      ) {
        Box(
          modifier = Modifier
            .padding(16.dp)
        ) {
          Text(
            text = "Pokémon",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFFFFFFFF)
          )
        }
        Box(
          modifier = Modifier
            .fillMaxSize()
            .alpha(0.5f)
            .offset(x = 20.dp, y = 20.dp),
          contentAlignment = Alignment.BottomEnd
        ) {
          Icon(
            painter = painterResource(Res.drawable.pokeball),
            modifier = Modifier.rotate(-45f),
            contentDescription = null,
            tint = Color(0xFFFFFFFF),
          )
        }
      }
    }
    items(menuItems.size) { item ->
      MenuItemRenderer(menuItems[item])
    }
  }
}

@Composable

fun MenuItemRenderer(
  item: MenuItemContent
) {
  Box(
    modifier = Modifier
      .padding(end = 8.dp, start = 8.dp, bottom = 16.dp)
      .fillMaxWidth()
      .height(120.dp)
      .clip(RoundedCornerShape(16.dp))
      .background(item.color)
      .clickable(enabled = true, onClick = item.onClick),
    contentAlignment = Alignment.BottomStart
  ) {
    Box(
      modifier = Modifier
        .padding(16.dp)
    ) {
      Text(
        text = item.title,
        style = MaterialTheme.typography.titleMedium,
        color = Color(0xFFFFFFFF)
      )
    }
    Box(
      modifier = Modifier
        .fillMaxSize()
        .alpha(0.5f)
        .offset(x = 20.dp, y = 20.dp),
      contentAlignment = Alignment.BottomEnd
    ) {
      Icon(
        painter = painterResource(item.icon),
        modifier = Modifier.rotate(-25f),
        contentDescription = null,
        tint = Color(0xFFFFFFFF),
      )
    }
  }
}