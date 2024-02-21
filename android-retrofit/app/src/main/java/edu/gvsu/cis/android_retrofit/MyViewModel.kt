package edu.gvsu.cis.android_retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _persons =  MutableLiveData<MutableList<Person>>(mutableListOf())
    val persons: LiveData<MutableList<Person>> get() = _persons
    var apiEndpoint: RandomUserApi

    init {
        apiEndpoint = RandomUserClient.getInstance().create(RandomUserApi::class.java)
    }

     fun getNames(count:Int) {
        println("Inside ViewModel getNames($count)")
        viewModelScope.launch{
            val rNames = apiEndpoint.getRandomNames(count)
            rNames.body()?.let {
//                val temp = mutableListOf<Person>()
//                temp.addAll(_persons.value!!)
//                temp.addAll(it.results)
                println("Info field is ${it.info}")
                _persons.value!!.addAll(it.results)
                _persons.postValue(_persons.value)
            }
        }
    }

}