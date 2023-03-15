package edu.gvsu.cis.dulimarta.arch_component

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var counter = 1
    fun addCounter() {
        counter++
    }
}