package com.ajax.ajaxtestassignment.ui.start

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajax.ajaxtestassignment.R
import com.ajax.ajaxtestassignment.data.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_contact_list.view.*

class ContactAdapter(
    var items: List<User>,
    val click: (User) -> Unit,
    val delete: (User) -> Unit
    ) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_contact_list,
            parent,
            false
        )
        view.setOnClickListener { v ->
            (v.tag as? User)?.let(click)
        }
        view.delete.setOnClickListener { v ->
            (v.tag as? User)?.let(delete)
        }

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = items[position]
        holder.itemView.tag = user
        holder.delete.tag = user
        holder.text.text = user.run { "$firstName $lastName" }

        Picasso.with(holder.image.context)
            .load(user.photo)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val text = view.text
    val image = view.image
    val delete = view.delete
}