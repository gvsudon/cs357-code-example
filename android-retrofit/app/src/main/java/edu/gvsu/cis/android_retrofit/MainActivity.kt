package edu.gvsu.cis.android_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
        val aButton = findViewById<Button>(R.id.ebird_btn)
        aButton.setOnClickListener {
            if (VM.getEBirdRegions())
                Snackbar.make(aButton, "Check Your LogCat for Ebird Responses", Snackbar.LENGTH_LONG)
                .show()
            else
                Snackbar.make(aButton, "Ebird Request failed. Check LogCat for details", Snackbar.LENGTH_LONG)
                    .show()
        }
        myList.adapter = NameAdapter(this, VM.persons.value!!)
        myList.layoutManager = LinearLayoutManager(this)
        VM.persons.observe(this) {
            myList.adapter?.notifyDataSetChanged()
        }
    }
}