package edu.gvsu.cis.ktor_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val VM by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myList = findViewById<RecyclerView>(R.id.name_list)
        val eBirdBtn = findViewById<Button>(R.id.ebird_btn)
        findViewById<Button>(R.id.fetchBtn).setOnClickListener {
            VM.getNames(10)
        }

        eBirdBtn.setOnClickListener {
            VM.getEbirdRegions("US-MI")
            Snackbar.make(eBirdBtn, "Check your LogCat for EBird output", Snackbar.LENGTH_LONG)
                .show()
        }
        myList.adapter = NameAdapter(VM.persons.value!!)
        myList.layoutManager = LinearLayoutManager(this)
        VM.persons.observe(this) {
            myList.adapter?.notifyDataSetChanged()
        }
    }
}