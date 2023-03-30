package edu.gvsu.cis.dulimarta.recycleitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.serpro69.kfaker.faker
import kotlin.random.Random

data class Person(
    val firstName: String, val lastName: String,
    val age: Int, val address: String
)

class MainActivityViewModel : ViewModel() {
    private val _data = MutableLiveData<MutableList<Person>>(mutableListOf())
    val persons get(): LiveData<MutableList<Person>> = _data
    val fakeSource = faker { }

    init {
        repeat(100) {
            val p = Person(
                firstName = fakeSource.name.firstName(),
                lastName = fakeSource.name.lastName(),
                age = Random.nextInt(20, 70),
                address = fakeSource.address.cityWithState()
            )
            _data.value!!.add(p)
        }
    }

    fun addOneMore() {
        val p = Person(
            firstName = fakeSource.name.firstName(),
            lastName = fakeSource.name.lastName(),
            age = Random.nextInt(20, 70),
            address = fakeSource.address.cityWithState()
        )
        val currentList = _data.value!!
        currentList.add(p)
        _data.postValue(currentList)
    }

    fun sortByFirstName() {
        val currentList = _data.value!!
        currentList.sortBy {
            it.firstName
        }
        _data.postValue(currentList)
    }

    fun sortByLastName() {
        val currentList = _data.value!!
        currentList.sortBy {
            it.lastName
        }
        _data.postValue(currentList)
    }
}