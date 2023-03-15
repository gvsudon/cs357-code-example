package edu.gvsu.cis.dulimarta.arch_component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

// Objective: show the execution order of lifecycle functions
class MainActivity : AppCompatActivity() {
    lateinit var label:TextView
    lateinit var myViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("onCreate called")
        myViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val addBtn = findViewById<Button>(R.id.add_button)
        label = findViewById<TextView>(R.id.text_label)

        addBtn.setOnClickListener {
            myViewModel.addCounter()
            label.text = myViewModel.counter.toString()
        }
    }

    override fun onResume() {
        super.onResume()
        println("onResume is called")
        label.text = myViewModel.counter.toString()
    }
}