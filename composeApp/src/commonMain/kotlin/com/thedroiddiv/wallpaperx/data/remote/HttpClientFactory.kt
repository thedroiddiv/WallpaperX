package com.thedroiddiv.wallpaperx.data.remote

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Builds a Ktor [HttpClient] configured with:
 * - A base URL applied to every request via [defaultRequest]
 * - An auth query parameter appended to every request (equivalent to the
 *   OkHttp interceptors used in the Retrofit setup in :data)
 * - kotlinx-serialization JSON content negotiation
 * - Napier-backed request/response logging
 *
 * The engine (OkHttp / Darwin / CIO / JS) is resolved at runtime from
 * whichever platform-specific artifact is on the classpath.
 */
internal fun buildHttpClient(
    baseUrl: String,
    authParamName: String,
    authParamValue: String,
): HttpClient = HttpClient {
    expectSuccess = true

    defaultRequest {
        url(baseUrl)
        parameter(authParamName, authParamValue)
    }

    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
        )
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Napier.v(tag = "Ktor", message = message)
            }
        }
        level = LogLevel.BODY
    }
}
