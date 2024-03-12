package edu.gvsu.cis.ktor_client

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json


const val TIME_OUT = 6000
val userClient = HttpClient(Android) {
    // Some of the config options below are intentionally commented out
    // For your own experiment, uncomment selected parts
    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
//            prettyPrint = true
//            isLenient = true
            ignoreUnknownKeys = true
        })

//        engine {
//            connectTimeout = TIME_OUT
//            socketTimeout = TIME_OUT
//        }
    }

//    install(Logging) {
//        logger = object: Logger {
//            override fun log(message: String) {
//                Log.v("Logger Ktor => ", message)
//            }
//        }
//    }
//
//    install(ResponseObserver) {
//        onResponse {
//            Log.d("HTTP Status: ", "${it.status.value}")
//        }
//    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }

}

object RandomUserClient {
    val RANDOMUSER_BASE_URL = "https://randomuser.me"
    suspend fun getRandomNames(N: Int): RandomName =
        userClient.request {
            url {
                protocol = URLProtocol.HTTPS
                host = "randomuser.me"
                path("api")
                parameters.append("inc", "name,email")
                parameters.append("results", N.toString())
            }
        }

    suspend fun getRandomNamesURLString(N: Int): RandomName =
        userClient.get("$RANDOMUSER_BASE_URL/api/?inc=name,email&results=${N}")
}

object EBirdApiClient {
    suspend fun getAdjacentRegions(region: String): Array<Region> =
        userClient.get {
            headers {
                append("X-eBirdApiToken", "fmr1l0nr9cht")
            }
            url {
                protocol = URLProtocol.HTTPS
                host = "api.ebird.org"
                path("v2/ref/adjacent/${region}")
            }
        }
}


object QuoteApiClient {
    val QUOTABLE_BASE_URL = "https://api.quotable.io"

    suspend fun getQuotes(N: Int): Array<Quote> =
        userClient.get("$QUOTABLE_BASE_URL/quotes/random?limit=$N")
}