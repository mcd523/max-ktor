package com.example.services

import com.example.plugins.graphql.Author
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AuthorService {
    companion object {
        val log: Logger = LoggerFactory.getLogger(AuthorService::class.java)
        val authors = listOf(
            Author(1, "Max"),
            Author(2, "Rachel"),
            Author(3, "Kuhn"),
            Author(4, "Bug"),
            Author(5, "Perry"),
        )
    }

    fun getAuthor(id: Int): Author? {
        return authors.find { it.authorId == id }
    }

    fun myMethod() {
        repeat(200) {
            CoroutineScope(Dispatchers.IO).launch {
                log.info("IndependentService::myMethod $it in coroutine ${this.coroutineContext}")
            }
        }
    }
}
