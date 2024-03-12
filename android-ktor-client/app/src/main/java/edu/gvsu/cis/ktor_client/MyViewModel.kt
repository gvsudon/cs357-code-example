package edu.gvsu.cis.ktor_client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _persons =  MutableLiveData<MutableList<Person>>(mutableListOf())
    val persons: LiveData<MutableList<Person>> get() = _persons
    
     fun getNames(count:Int) {
        println("Inside ViewModel getNames($count)")
        viewModelScope.launch(Dispatchers.IO){
            val rNames = RandomUserClient.getRandomNames(count)
            println("Response is $rNames")
                _persons.value!!.addAll(rNames.results)
                _persons.postValue(_persons.value)

            val qs = QuoteApiClient.getQuotes(4)
            for (q in qs) {
                println("Quote $q")
            }
        }
    }

    fun getEbirdRegions(where: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val regions = EBirdApiClient.getAdjacentRegions(where)
            for (r in regions) {
                println("EBird Region ${r}")
            }

        }
    }

}