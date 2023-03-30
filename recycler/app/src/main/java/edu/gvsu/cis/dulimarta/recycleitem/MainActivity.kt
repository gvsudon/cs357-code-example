package edu.gvsu.cis.dulimarta.recycleitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.my_list)
        recyclerView.adapter = MyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = view.findViewById(R.id.the_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = 71

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = "Hello world at row $position"
    }
}