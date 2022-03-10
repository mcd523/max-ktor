package com.example.plugins

import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SerializationConfiguration {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(SerializationConfiguration::class.java)

        fun configure(app: Application) {
            app.install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                    setLenient()
                }
            }
        }
    }
}
