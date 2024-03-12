package edu.gvsu.cis.ktor_client

import android.health.connect.datatypes.units.Length
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//import com.google.gson.annotations.SerializedName

@Serializable
data class Name(val title:String, val first:String, val last:String)

@Serializable
data class Person(val name:Name, val email:String)

@Serializable
data class Info(val seed: String, @SerialName("results") val count: Int, val page: Int, val version:String)

@Serializable
data class RandomName(
    val results: List<Person>,
    val info: Info
    //or val info: Any
)

@Serializable
data class Quote(val content: String, val author: String, val tags: List<String>, val length: Int)


@Serializable
data class Region(val code:String, val name: String)