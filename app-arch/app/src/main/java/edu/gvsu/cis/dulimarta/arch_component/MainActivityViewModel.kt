package edu.gvsu.cis.dulimarta.arch_component

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    val _counter = MutableLiveData<Int>(1)
    val counter: LiveData<Int> get() = _counter
    fun addCounter() {
        _counter.value = _counter.value?.inc()
    }
}