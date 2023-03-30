package edu.gvsu.cis.dulimarta.recycleitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.serpro69.kfaker.faker

data class Person(val firstName: String, val lastName: String)

class MainActivityViewModel:ViewModel() {
    private val _data = MutableLiveData<MutableList<Person>>(mutableListOf())
    val data get(): LiveData<MutableList<Person>> = _data

    init {
        val fakeSource = faker {  }
        repeat(100) {
            val fName = fakeSource.name.firstName()
            val lName = fakeSource.name.lastName()
            _data.value!!.add(Person(fName, lName))
        }
    }
}