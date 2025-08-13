package dev.jesusgonzalez.kotlinproject.util

fun getIdFromUrl(url: String): Int {
  return url.trimEnd('/').substringAfterLast('/').toInt()
}