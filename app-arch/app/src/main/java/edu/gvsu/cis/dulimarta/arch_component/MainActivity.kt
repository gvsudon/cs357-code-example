package edu.gvsu.cis.dulimarta.arch_component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

// Objective: show the execution order of lifecycle functions
class MainActivity : AppCompatActivity() {
    lateinit var myViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("onCreate called")
        myViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val addBtn = findViewById<Button>(R.id.add_button)
        val label = findViewById<TextView>(R.id.text_label)

        myViewModel.counter.observe(this) {
            label.text = it.toString()
        }
        addBtn.setOnClickListener {
            myViewModel.addCounter()
        }
    }
}

class BadMainActivity: AppCompatActivity() {
    private var counter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addBtn = findViewById<Button>(R.id.add_button)
        val label = findViewById<TextView>(R.id.text_label)
        label.text = counter.toString()
        addBtn.setOnClickListener {
            counter ++
            label.text = counter.toString()
        }
    }
}