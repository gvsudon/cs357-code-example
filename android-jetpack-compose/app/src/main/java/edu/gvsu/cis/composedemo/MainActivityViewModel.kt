package edu.gvsu.cis.composedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    private val _counter: MutableLiveData<Int> = MutableLiveData(18)
    val counter: LiveData<Int> get() = _counter

    fun countUp() {
        _counter.postValue(_counter.value!! + 1)
    }
}