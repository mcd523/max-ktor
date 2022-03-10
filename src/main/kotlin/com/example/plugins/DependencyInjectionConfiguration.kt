package com.example.plugins

import com.example.plugins.graphql.GraphQLConfiguration
import com.example.services.DependentService
import com.example.services.IndependentService
import com.google.gson.GsonBuilder
import org.kodein.di.DI
import org.kodein.di.bindSingleton

class DependencyInjectionConfiguration {
    companion object {
        fun createDI(): DI {
            return DI {
                bindSingleton { DependentService() }
                bindSingleton { IndependentService() }
                bindSingleton { GraphQLConfiguration() }
                bindSingleton {
                    GsonBuilder()
                        .setPrettyPrinting()
                        .create()
                }
            }
        }
    }
}
