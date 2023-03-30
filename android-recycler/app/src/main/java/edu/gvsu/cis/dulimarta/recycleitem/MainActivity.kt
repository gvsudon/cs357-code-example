package edu.gvsu.cis.dulimarta.recycleitem

import android.content.res.Configuration
import android.os.Bundle
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
        recyclerView.adapter = MyAdapter(myViewModel.persons.value!!) {
            Snackbar.make(
                recyclerView,
                "You select ${it.firstName} ${it.lastName}",
                Snackbar.LENGTH_LONG
            )
                .show()
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
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        else
            recyclerView.layoutManager = LinearLayoutManager(this)
    }
}