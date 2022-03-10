package com.example.services

import com.example.MaxKtorApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DependentService: DIAware {
    private val log: Logger = LoggerFactory.getLogger(DependentService::class.java)
    override val di: DI by lazy { MaxKtorApplication.di }

    private val independentService: IndependentService by instance()

    suspend fun doStuff() {
        // Run blocking method on separate dispatcher from main app
        withContext(Dispatchers.IO) {
            log.info("Hello from coroutine ${this.coroutineContext}")
            independentService.myMethod()
        }
    }
}
