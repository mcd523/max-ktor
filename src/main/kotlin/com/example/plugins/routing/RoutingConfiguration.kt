package com.example.plugins

import com.example.plugins.routing.Routes
import com.example.plugins.routing.locations.MyLocation
import com.example.plugins.routing.locations.Type
import com.example.services.DependentService
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import org.kodein.di.DI
import org.kodein.di.instance

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
