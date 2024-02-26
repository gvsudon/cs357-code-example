package edu.gvsu.cis.android_retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NameAdapter (val ctx: Context,
    val names: List<PersonSimplified>): RecyclerView.Adapter<NameAdapter.NameViewHolder>() {
    class NameViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val email:TextView
        val avatar: ImageView
        init {
            name = itemView.findViewById(R.id.name)
            email = itemView.findViewById(R.id.email)
            avatar = itemView.findViewById(R.id.avatar)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        // Instead of using our own custom layout, this code uses the predefined
        // layout simple_list_item_2 which has two TextView called text1 and text2
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return NameViewHolder(v)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val p = names[position]
        holder.name.text = "(${position+1}) ${p.name}"
        holder.email.text = p.email
        Picasso.with(ctx).load(p.imageURL).into(holder.avatar)
    }
}