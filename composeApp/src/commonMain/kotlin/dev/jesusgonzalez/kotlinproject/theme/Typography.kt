package dev.jesusgonzalez.kotlinproject.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.inter_medium
import kotlinproject.composeapp.generated.resources.inter_regular
import kotlinproject.composeapp.generated.resources.space_grotesk_bold
import org.jetbrains.compose.resources.Font
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

val SpaceGrotesk @Composable get() = FontFamily(
    Font(
        resource = Res.font.space_grotesk_bold,
        weight = FontWeight.Bold
    )
)

val Inter @Composable get() = FontFamily(
    Font(
        resource = Res.font.inter_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resource = Res.font.inter_regular,
        weight = FontWeight.Normal
    )
)

val Typography: Typography @Composable get() = Typography(
    bodyLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 56.sp,
        lineHeight = 60.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 36.sp
    ),
    titleSmall = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        lineHeight = 24.sp
    ),
)