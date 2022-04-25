package com.example.services

import com.example.MaxKtorApplication
import com.example.plugins.graphql.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ArticleService: DIAware {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(ArticleService::class.java)
        private val articles = listOf(
            Article(1, "Article 1.0", 1),
            Article(2, "Article 1.1", 1),
            Article(3, "Article 2.0", 2),
            Article(4, "Article 2.1", 2),
            Article(5, "Article 3.0", 3),
            Article(6, "Article 3.1", 3),
            Article(7, "Article 4.0", 4),
            Article(8, "Article 4.1", 4),
        )
    }
    override val di: DI by lazy { MaxKtorApplication.di }

    private val authorService: AuthorService by instance()

    // Returns all articles with the given authorId. If authorId is null then return all articles
    fun getArticlesByAuthor(authorId: Int?): List<Article> {
        return authorId?.let {
            articles
                .filter { it.author?.authorId == authorId }
                .mapNotNull { it.hydrateArticle() }
        } ?: articles
    }

    fun getArticleById(articleId: Int): Article? {
        return articles
            .find { it.articleId == articleId }
            ?.hydrateArticle()
    }

    fun getArticleByName(name: String): Article? {
        return articles
            .find { it.articleName == name }
            ?.hydrateArticle()
    }

    private fun Article.hydrateArticle(): Article? {
        return this.author?.authorId?.let {
            this.copy(author = authorService.getAuthor(it))
        }
    }

    suspend fun doStuff() {
        // Run blocking method on separate dispatcher from main app
        withContext(Dispatchers.IO) {
            log.info("Hello from coroutine ${this.coroutineContext}")
            authorService.myMethod()
        }
    }
}
