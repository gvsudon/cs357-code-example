package edu.gvsu.cis.android_retrofit

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Name(val title:String, val first:String, val last:String)
data class Person(val name:Name, val email:String)

data class Info(val seed: String, @SerializedName("results") val count: Int, val page: Int, val Version:String)
data class RandomName(
    val results: List<Person>,
    val info: Info
    //or val info: Any
)



