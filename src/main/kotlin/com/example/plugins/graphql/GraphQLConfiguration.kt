package com.example.plugins.graphql

import com.apurebase.kgraphql.KGraphQL
import com.example.MaxKtorApplication
import com.example.services.ArticleService
import com.example.services.AuthorService
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GraphQLConfiguration: DIAware {
    companion object {
        val log: Logger = LoggerFactory.getLogger(GraphQLConfiguration::class.java)
    }

    override val di: DI by lazy { MaxKtorApplication.di }
    private val authorService: AuthorService by instance()
    private val articleService: ArticleService by instance()

    val schema = KGraphQL.schema {
        query("article") {
            resolver { articleId: Int?, articleName: String?, authorId: Int? ->
                log.info("Article received vars: id:$articleId articleName:$articleName author:$authorId")
                return@resolver when {
                    articleId != null -> listOf(articleService.getArticleById(articleId))
                    else -> articleService.getArticlesByAuthor(authorId)
                }
            }
        }
        type<Article>()

        query("author") {
            resolver { authorId: Int?, name: String? ->
                log.info("Author received vars: id:$authorId name:$name")
                authorId?.let {
                    authorService.getAuthor(it)
                }
            }.withArgs {
                arg<Int> { name = "authorId"; defaultValue = 3 }
                arg<String> { name = "name"; defaultValue = "" }
            }
        }
        type<Author>()
    }

}
