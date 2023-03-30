package edu.gvsu.cis.dulimarta.recycleitem

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var myViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        recyclerView = findViewById<RecyclerView>(R.id.my_list)
        recyclerView.adapter =
            MyAdapter(myViewModel.persons.value!!) { p: Person, isLongClick: Boolean ->
                if (isLongClick) {
                    val personDetails = Intent(this, PersonDetailsActivity::class.java)
                    personDetails.putExtra("firstName", p.firstName)
                    personDetails.putExtra("lastName", p.lastName)
                    personDetails.putExtra("age", p.age)
                    personDetails.putExtra("address", p.address)
                    startActivity(personDetails)
                } else {
                    Snackbar.make(
                        recyclerView,
                        "You select ${p.firstName} ${p.lastName}",
                        Snackbar.LENGTH_LONG
                    ).show()

                }
            }
        recyclerView.layoutManager = LinearLayoutManager(this)

        myViewModel.persons.observe(this) {
            recyclerView.adapter?.notifyDataSetChanged()
            Snackbar.make(
                recyclerView,
                "Total items on the list ${it.size}",
                Snackbar.LENGTH_LONG
            )
                .show()
        }
        val addBtn = findViewById<FloatingActionButton>(R.id.add_fab)
        addBtn.setOnClickListener {
            myViewModel.addOneMore()
        }
        findViewById<Button>(R.id.sort_by_first).setOnClickListener {
            myViewModel.sortByFirstName()
        }
        findViewById<Button>(R.id.sort_by_last).setOnClickListener {
            myViewModel.sortByLastName()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        else
            recyclerView.layoutManager = LinearLayoutManager(this)
    }
}