package com.numpol.cmptodo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform