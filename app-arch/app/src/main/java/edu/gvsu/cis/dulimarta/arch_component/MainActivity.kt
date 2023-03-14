package edu.gvsu.cis.dulimarta.arch_component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

// Objective: show the execution order of lifecycle functions
class MainActivity : AppCompatActivity() {
    var count = 0
    lateinit var label:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("onCreate called")
        val addBtn = findViewById<Button>(R.id.add_button)
        label = findViewById<TextView>(R.id.text_label)

        addBtn.setOnClickListener {
            count++
            label.text = count.toString()
        }
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart is called")
    }

    override fun onStart() {
        super.onStart()
        println("onStart is called")
    }
    override fun onResume() {
        super.onResume()
        println("onResume is called")
        label.text = count.toString()
    }

    override fun onPause() {
        super.onPause()
        println("onPause is called")
    }

    override fun onStop() {
        super.onStop()
        println("onStop is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy is called")
    }
}