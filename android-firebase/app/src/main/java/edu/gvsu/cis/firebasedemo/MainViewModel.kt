package edu.gvsu.cis.firebasedemo

import androidx.lifecycle.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.github.serpro69.kfaker.faker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// The declaration of the data class must allow default constructor
data class Person(val firstName: String = "", val lastName: String = "")
class MainViewModel : ViewModel() {
    private val _uid:MutableLiveData<String?> = MutableLiveData(null)
    private val _persons:MutableLiveData<List<Person>> = MutableLiveData(listOf())

    private val auth = Firebase.auth
    private val db = Firebase.firestore
    val uid: LiveData<String?> get() = _uid
    val persons: LiveData<List<Person>> get() = _persons
    val fakeSource = faker {}
    init {
        _uid.postValue(auth.uid)
        db.collection("members").addSnapshotListener { value, error ->
            value?.let {
                for ((index,chg) in it.documentChanges.withIndex()) {
                    val uDoc = chg.document.toObject(Person::class.java)
                    println("${chg.document.id} ${chg.type.name} $uDoc")
                }
            }
        }
        fetchMembers()
    }

    fun addOne() {
        val p = Person(fakeSource.name.firstName(), fakeSource.name.lastName())
        viewModelScope.launch(Dispatchers.IO) {
            val coll = db.collection("members")
                .add(p)
        }
    }

    fun fetchMembers() {
//        _persons.value?.run {
//            if (size > 0) return
//        }
        viewModelScope.launch(Dispatchers.IO) {
            val allMembers = mutableListOf<Person>()
            db.collection("members")
                .get()
                .addOnSuccessListener {
                    for (doc in it.documents) {
                        doc.toObject(Person::class.java)?.let {
                            println("${doc.id} ${it.firstName} ${it.lastName}")
                            allMembers.add(it)
                        }
                    }
                    _persons.postValue(allMembers)
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