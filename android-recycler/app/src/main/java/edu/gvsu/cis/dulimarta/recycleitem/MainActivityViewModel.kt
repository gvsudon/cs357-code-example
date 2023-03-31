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
    private val _updateType = MutableLiveData<Int>(-1)
    // _updateType: MAX_INT implies the entire table must be refreshed
    // _updateType: [0, MAX_INT) implies only a specific row must be refreshed
    val updateType get(): LiveData<Int> = _updateType
    private val _persons = MutableLiveData<MutableList<Person>> (mutableListOf())
    val persons get(): LiveData<MutableList<Person>> =  _persons
    val fakeSource = faker { }

    init {
        repeat(100) {
            val p = Person(
                firstName = fakeSource.name.firstName(),
                lastName = fakeSource.name.lastName(),
                age = Random.nextInt(20, 70),
                address = fakeSource.address.cityWithState()
            )
            _persons.value!!.add(p)
        }
    }

    fun addOneMore() {
        val p = Person(
            firstName = fakeSource.name.firstName(),
            lastName = fakeSource.name.lastName(),
            age = Random.nextInt(20, 70),
            address = fakeSource.address.cityWithState()
        )
        _persons.value!!.add(p)
        _updateType.postValue(_persons.value!!.size - 1)
    }

    fun sortByFirstName() {
        _persons.value!!.sortBy {
            it.firstName
        }
        _updateType.postValue(Int.MAX_VALUE)
    }

    fun sortByLastName() {
        _persons.value!!.sortBy {
            it.lastName
        }
        _updateType.postValue(Int.MAX_VALUE)
    }

    fun deleteOne(atPos: Int) {
        _persons.value!!.removeAt(atPos)
        _updateType.postValue(Int.MAX_VALUE)
    }
}