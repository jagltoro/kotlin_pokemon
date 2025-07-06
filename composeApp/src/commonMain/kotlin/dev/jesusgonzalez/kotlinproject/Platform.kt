package dev.jesusgonzalez.kotlinproject

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform