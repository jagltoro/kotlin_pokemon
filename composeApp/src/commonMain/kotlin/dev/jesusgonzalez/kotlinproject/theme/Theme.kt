package dev.jesusgonzalez.kotlinproject.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorTheme = lightColorScheme(
    primary = Primary,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceContainerLowest = SurfaceLowest,
    background = Background
)

val DarkColorTheme = darkColorScheme(
    primary = Primary,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceContainerLowest = SurfaceLowest,
    background = Background
)

@Composable
fun Theme(
    content: @Composable () -> Unit
){
    val isDarkTheme = isSystemInDarkTheme()
    MaterialTheme(
        content = content,
        colorScheme = if(isDarkTheme) DarkColorTheme else LightColorTheme,
        typography = Typography
    )
}