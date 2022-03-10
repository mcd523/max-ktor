package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.kodein.di.DI

object MaxKtorApplication {

    lateinit var di: DI

    @JvmStatic
    fun main(args: Array<String>) {
        embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
            di = DependencyInjectionConfiguration.createDI()
            MonitoringConfiguration.configure(this)
            RoutingConfiguration.configure(this)
            SerializationConfiguration.configure(this)
        }.start(wait = true)
    }
}
