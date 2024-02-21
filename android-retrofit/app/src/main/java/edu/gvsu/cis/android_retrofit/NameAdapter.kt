package edu.gvsu.cis.android_retrofit

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class NameAdapter (val names: List<Person>): RecyclerView.Adapter<NameAdapter.NameViewHolder>() {
    class NameViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var topText: TextView
        var bottomText:TextView
        init {
            topText = itemView.findViewById(android.R.id.text1)
            bottomText = itemView.findViewById(android.R.id.text2)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        // Instead of using our own custom layout, this code uses the predefined
        // layout simple_list_item_2 which has two TextView called text1 and text2
        val v = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return NameViewHolder(v)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val p = names[position]
        holder.topText.text = "($position) ${p.name.first} ${p.name.last}"
        holder.bottomText.text = p.email
    }
}