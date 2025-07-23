package dev.jesusgonzalez.kotlinproject.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
actual fun getScreenWidth(): Dp = LocalWindowInfo.current.containerSize.width.dp

@Composable
actual fun getScreenHeight(): Dp = LocalWindowInfo.current.containerSize.height.dp