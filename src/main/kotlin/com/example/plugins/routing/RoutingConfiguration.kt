package com.example.plugins

import com.example.plugins.routing.Routes
import io.ktor.server.locations.*
import io.ktor.server.plugins.*
import io.ktor.server.application.*

@OptIn(KtorExperimentalLocationsAPI::class)
class RoutingConfiguration {
    companion object {
        fun configure(app: Application) {
            app.install(Locations) {
            }
            app.install(AutoHeadResponse)

            Routes().createRoutes(app)
        }
    }

}
