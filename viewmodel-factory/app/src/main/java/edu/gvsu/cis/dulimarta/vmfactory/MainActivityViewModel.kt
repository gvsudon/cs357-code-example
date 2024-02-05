package edu.gvsu.cis.dulimarta.vmfactory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModel(val countryCode: String): ViewModel() {
    private val _countryName = MutableLiveData("")
    val countryName: LiveData<String> get() = _countryName
    init {
        println("Country code for ViewModel is ${countryCode}")
        _countryName.value = when (countryCode) {
            "US" -> "United States"
            "UK" -> "United Kingdom"
            "DE" -> "Germany"
            "ID" -> "Indonesia"
            else -> "Unknown"
        }
    }
    fun doNothing() {

    }
}


class VMFactory(val code:String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(code) as T
    }
}