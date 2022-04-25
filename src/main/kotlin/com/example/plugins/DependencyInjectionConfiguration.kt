package com.example.plugins

import com.example.plugins.graphql.GraphQLConfiguration
import com.example.services.ArticleService
import com.example.services.AuthorService
import com.google.gson.GsonBuilder
import org.kodein.di.DI
import org.kodein.di.bindSingleton

class DependencyInjectionConfiguration {
    companion object {
        fun createDI(): DI {
            return DI {
                bindSingleton { ArticleService() }
                bindSingleton { AuthorService() }
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
