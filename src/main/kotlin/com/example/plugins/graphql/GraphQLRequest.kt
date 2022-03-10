package com.example.plugins.graphql

data class GraphQlRequest(val query: String = "", val variables: Map<String, Any>? = emptyMap())
