package com.plcoding.translator_kmm.translate.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

actual class HttpClientFactory {
    actual fun create(): HttpClient =
        HttpClient(Android){
            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000
            }
            install(ContentNegotiation){
                json()
            }
        }
}