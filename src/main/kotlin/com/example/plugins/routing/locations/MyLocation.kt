package com.example.plugins.routing.locations

import io.ktor.server.locations.*

@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")
