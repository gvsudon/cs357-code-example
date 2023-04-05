package edu.gvsu.cis.dulimarta.recycleitem

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    val myViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        myViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
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
        val swipeHelper: ItemTouchHelper = ItemTouchHelper(rightSwipeCallback)
        swipeHelper.attachToRecyclerView(recyclerView)
        myViewModel.updateType.observe(this) {
            if (it == Int.MAX_VALUE)
                recyclerView.adapter?.notifyDataSetChanged()
            else
                recyclerView.adapter?.notifyItemChanged(it)
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
        // Use two-column format in landscape mode
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        else
            recyclerView.layoutManager = LinearLayoutManager(this)
    }

    val rightSwipeCallback = object : ItemTouchHelper.SimpleCallback(
        0 /* ignore row drag */,
        ItemTouchHelper.RIGHT /* handle write swipe only */
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            // Ignore moving rows in the list
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.absoluteAdapterPosition
            myViewModel.persons.value?.get(position)?.let {
                val deleteHandler = Handler(Looper.getMainLooper())
                deleteHandler.postDelayed({
                    myViewModel.deleteOne(position)
                }, 5000)
                val msg = "${it.firstName} ${it.lastName} will be deleted in 5 seconds"
                Snackbar.make(viewHolder.itemView, msg, 5000)
                    .setAction("Undo") {
                        deleteHandler.removeCallbacksAndMessages(null)
                        recyclerView.adapter?.notifyDataSetChanged()
                    }
                    .show()
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }

    }
}