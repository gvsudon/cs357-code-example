package edu.gvsu.cis.android_retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomUserApi {
    // Query string is     inc=name,email&results=N
    @GET("api/?inc=name,email,picture")
    suspend fun getRandomNames(@Query("results") N:Int): Response<RandomName>
}

val logInterceptor = HttpLoggingInterceptor()

object RandomUserClient {
    val BASE_URL = "https://randomuser.me/"
    val okHttpClientBuilder  = OkHttpClient.Builder()

    fun getInstance(): Retrofit {
        logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        okHttpClientBuilder.addInterceptor(logInterceptor)

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }
}

interface EBirdApi {
    // This API will fail UNLESS you replace xxxxx below with your own Ebird API Key
    @Headers("x-ebirdapitoken: xxxxx")
    @GET("v2/ref/adjacent/{region}")
    suspend fun getAdjacentRegionsTo(@Path("region") regionCode:String): Response<List<Region>>
}


object EBirdClient {
    val BASE_URL = "https://api.ebird.org"
    val okHttpClientBuilder = OkHttpClient.Builder()
    fun getInstance(): Retrofit {
        logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        okHttpClientBuilder.addInterceptor(logInterceptor)
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }
}