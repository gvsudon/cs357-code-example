package edu.gvsu.cis.firebasedemo

import androidx.lifecycle.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import io.github.serpro69.kfaker.faker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// The declaration of the data class must allow default constructor
data class Person(val firstName: String = "", val lastName: String = "")
class MainViewModel : ViewModel() {
    private val _uid:MutableLiveData<String?> = MutableLiveData(null)
    private val _persons = MutableLiveData<MutableList<Person>>(mutableListOf<Person>())

    private val auth = Firebase.auth
    private val db = Firebase.firestore
    val uid: LiveData<String?> get() = _uid
    val persons: LiveData<MutableList<Person>> get() = _persons
    val fakeSource = faker {}
    init {
//        println("Firestore instance ${db}")
        _uid.postValue(auth.uid)
//        fetchMembers()
    }

    fun addOne() {
        val p = Person(fakeSource.name.firstName(), fakeSource.name.lastName())
        _persons.value!!.add(p);
        _persons.postValue(_persons.value)
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("members")
                .add(p)
        }
    }

    fun fetchMembers() {
        viewModelScope.launch(Dispatchers.IO) {
//            val allMembers = mutableListOf<Person>()
            db.collection("members")

                .get()
                .addOnSuccessListener {
                    for (doc in it.documents) {
                        doc.toObject(Person::class.java)?.let {
//                            println("${doc.id} ${it.firstName} ${it.lastName}")
                            _persons.value?.add(it)
                        }
                    }
                    _persons.value!!.sortBy {
                        it.firstName
                    }
                    _persons.postValue(_persons.value)
                }
                .addOnFailureListener {
                    println("Unable to read collections $it")
                }
        }
    }
    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            auth.signOut()
            _uid.postValue(null)
        }
    }
}