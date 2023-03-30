package edu.gvsu.cis.dulimarta.recycleitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val persons: List<Person>, val selectListener: (Person, Boolean) -> Unit) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var selectedPos = RecyclerView.NO_POSITION
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

    override fun getItemCount(): Int = persons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.isSelected = selectedPos == position
        val who = persons.get(position)
        holder.textView.text = "${who.firstName} ${who.lastName}"
        holder.itemView.setOnClickListener {
            selectListener(who, false)
        }
        holder.itemView.setOnLongClickListener {
            selectListener(who, true)
            true
        }
    }



}