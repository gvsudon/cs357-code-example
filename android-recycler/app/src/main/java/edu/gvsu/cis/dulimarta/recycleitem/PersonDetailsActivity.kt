package edu.gvsu.cis.dulimarta.recycleitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PersonDetailsActivity : AppCompatActivity() {
    lateinit var firstName: TextView
    lateinit var lastName: TextView
    lateinit var city: TextView
    lateinit var age: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        firstName = findViewById<TextView>(R.id.first_name)
        lastName = findViewById<TextView>(R.id.last_name)
        city = findViewById<TextView>(R.id.city)
        age = findViewById<TextView>(R.id.age)
    }

    override fun onResume() {
        super.onResume()
        firstName.text = intent.getStringExtra("firstName")
        lastName.text = intent.getStringExtra("lastName")
        city.text = intent.getStringExtra("address")
        age.text = intent.getIntExtra("age", 0).toString()
    }
}