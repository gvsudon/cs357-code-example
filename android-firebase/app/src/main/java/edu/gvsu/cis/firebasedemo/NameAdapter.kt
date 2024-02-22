package edu.gvsu.cis.firebasedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.rpc.context.AttributeContext.Peer

class NameAdapter(val names: List<Person>): RecyclerView.Adapter<NameAdapter.NameViewHolder>() {
    class NameViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val myText: TextView
        init {
            myText = itemView.findViewById(android.R.id.text1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val z = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return NameViewHolder(z)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val n = names[position]
        holder.myText.text = "${position + 1} ${n.firstName} ${n.lastName}"
    }
}