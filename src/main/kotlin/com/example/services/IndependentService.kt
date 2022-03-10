package com.example.services

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class IndependentService {
    companion object {
        val log: Logger = LoggerFactory.getLogger(IndependentService::class.java)
    }

    fun myMethod() {
        repeat(200) {
            CoroutineScope(Dispatchers.IO).launch {
                log.info("IndependentService::myMethod $it in coroutine ${this.coroutineContext}")
            }
        }
    }
}
