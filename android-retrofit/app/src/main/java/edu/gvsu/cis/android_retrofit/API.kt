package edu.gvsu.cis.android_retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    // Query string is     inc=name,email&results=N
    @GET("api/?inc=name,email")
    suspend fun getRandomNames(@Query("results") N:Int): Response<RandomName>
}
object RandomUserClient {
    val BASE_URL = "https://randomuser.me/"
    val okHttpClientBuilder  = OkHttpClient.Builder()
    val logInterceptor = HttpLoggingInterceptor()

    fun getInstance(): Retrofit {
        logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        okHttpClientBuilder.addInterceptor(logInterceptor)

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }
}