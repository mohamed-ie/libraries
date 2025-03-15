package io.github.mohamed_ie.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform