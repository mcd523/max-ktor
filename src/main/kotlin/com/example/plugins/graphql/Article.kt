package com.example.plugins.graphql

data class Article(val articleId: Int?, val articleName: String?, val author: Author? = null) {
    constructor(articleId: Int?, articleName: String?, authorId: Int?): this(articleId, articleName, Author(authorId, null))
}
