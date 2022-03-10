package com.example.plugins.graphql

import com.apurebase.kgraphql.KGraphQL
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GraphQLConfiguration {
    companion object {
        val log: Logger = LoggerFactory.getLogger(GraphQLConfiguration::class.java)
    }

    val schema = KGraphQL.schema {
        query("article") {
            resolver { id: Int?, text: String ->
                log.info("Received vars: id:$id text:$text")
                Article(id, text)
            }.withArgs {
                arg<Int> { name = "id"; defaultValue = 1 }
                arg<String> { name = "text"; defaultValue = "Hello world!" }
            }
        }
        type<Article>()
    }

}
