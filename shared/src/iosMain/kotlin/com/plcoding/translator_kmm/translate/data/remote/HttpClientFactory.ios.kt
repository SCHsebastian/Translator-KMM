package com.plcoding.translator_kmm.translate.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

actual class HttpClientFactory {
    actual fun create(): HttpClient =
        HttpClient(Darwin){
            engine {
                configureRequest {
                    setTimeoutInterval(100000.0)
                }
            }
            install(ContentNegotiation){
                json()
            }
        }
}