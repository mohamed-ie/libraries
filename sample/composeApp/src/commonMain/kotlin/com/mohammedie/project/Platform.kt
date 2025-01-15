package com.mohammedie.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform