package edu.gvsu.cis.android_retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class PersonSimplified(val name: String, val email: String, val imageURL: String)
class MyViewModel : ViewModel() {
    private val _persons =  MutableLiveData<MutableList<PersonSimplified>>(mutableListOf())
    val persons: LiveData<MutableList<PersonSimplified>> get() = _persons
    var apiEndpoint: RandomUserApi
    val eBirdEndPoint: EBirdApi

    init {
        apiEndpoint = RandomUserClient.getInstance().create(RandomUserApi::class.java)
        eBirdEndPoint = EBirdClient.getInstance().create(EBirdApi::class.java)
    }

     fun getNames(count:Int) {
        println("Inside ViewModel getNames($count)")
        viewModelScope.launch(Dispatchers.IO){
            val rNames = apiEndpoint.getRandomNames(count)
            rNames.body()?.let {
                println("Info field is ${it.info}")
                _persons.value!!.addAll(it.results.map {
                    PersonSimplified(name = "${it.name.first} ${it.name.last}",
                        email = it.email,
                        imageURL = it.picture.thumbnail)
                })
                _persons.postValue(_persons.value)
            }


        }
    }

    fun getEBirdRegions(): Boolean {
        var outcome: Boolean = false
        viewModelScope.launch(Dispatchers.IO) {
            val response = eBirdEndPoint.getAdjacentRegionsTo("US-MI")
            if (response.isSuccessful()) {
                response.body()?.let { regions ->
                    println("Region(s) adjacent to UD-MI are")
                    for (r in regions) {
                        println(r)
                    }
                    outcome = true
                }
            } else {
                println("HTTP Error ${response.code()} ${response.message()}")
                outcome = false
            }
        }
        return outcome
    }
}