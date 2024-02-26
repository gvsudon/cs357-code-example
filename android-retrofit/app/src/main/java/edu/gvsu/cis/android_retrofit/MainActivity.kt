package edu.gvsu.cis.android_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.coroutineScope

class MainActivity : AppCompatActivity() {
    private val VM by viewModels<MyViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myList = findViewById<RecyclerView>(R.id.name_list)
        findViewById<Button>(R.id.fetchBtn).setOnClickListener {
                VM.getNames(10)
        }
        myList.adapter = NameAdapter(this, VM.persons.value!!)
        myList.layoutManager = LinearLayoutManager(this)
        VM.persons.observe(this) {
            myList.adapter?.notifyDataSetChanged()
        }
    }
}