package com.example.androidproject.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R

class InviteAdapter(private val listContatacts:List<ContactModel>): RecyclerView.Adapter<InviteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.item_invite, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: InviteAdapter.ViewHolder, position: Int) {
        val item=listContatacts[position]
        holder.name.text=item.name
    }



    override fun getItemCount(): Int {
        return listContatacts.size
    }


    class ViewHolder(private val item:View) : RecyclerView.ViewHolder(item) {
        val name=item.findViewById<TextView>(R.id.name)

    }
}