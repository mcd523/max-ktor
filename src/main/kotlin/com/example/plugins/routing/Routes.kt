package com.example.plugins.routing

import com.example.MaxKtorApplication
import com.example.plugins.graphql.GraphQLConfiguration
import com.example.plugins.graphql.GraphQlRequest
import com.example.plugins.routing.locations.MyLocation
import com.example.plugins.routing.locations.Type
import com.example.services.ArticleService
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Routes: DIAware {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(Routes::class.java)
    }

    override val di: DI by lazy { MaxKtorApplication.di }

    private val service: ArticleService by di.instance()
    private val graphQl: GraphQLConfiguration by di.instance()
    private val gson: Gson by di.instance()

    fun createRoutes(app: Application) {
        app.routing {
            get("/") {
                service.doStuff()
                call.respondText("Hello World!")
            }
            get<MyLocation> {
                call.respondText("Location: name=${it.name}, arg1=${it.arg1}, arg2=${it.arg2}")
            }
            // Register nested routes
            get<Type.Edit> {
                call.respondText("Inside $it")
            }
            get<Type.List> {
                call.respondText("Inside $it")
            }
            post("/graphql") {
                val request = call.receive<GraphQlRequest>()
                val variables = request.variables?.let { gson.toJson(request.variables) }

                log.info(request.toString())
                log.info(variables)

                val result = try {
                    graphQl.schema.execute(request.query, variables)
                } catch (e: Exception) {
                    log.error("Error processing graphql request.", e)
                    call.respondText(gson.toJson(mapOf("error" to e.message)), ContentType.Application.Json, HttpStatusCode.InternalServerError)
                    return@post
                }

                call.respondText(result, ContentType.Application.Json, HttpStatusCode.OK)
            }
        }
    }
}
