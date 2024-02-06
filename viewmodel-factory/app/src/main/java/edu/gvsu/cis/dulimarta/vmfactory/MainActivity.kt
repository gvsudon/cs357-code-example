package edu.gvsu.cis.dulimarta.vmfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    val vm by viewModels<MainActivityViewModel>() {
        VMFactory("ID")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.doNothing()
        println("Country name ${vm.countryName.value}")
    }
}