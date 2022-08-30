package com.example.weatherapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.ItemUserDetailsBinding
import com.example.weatherapplication.model.UserDetails

class ListAdapter(private val itemInterface: ItemClickListener): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<UserDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = ItemUserDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val users: UserDetails = userList[position]
        holder.bind(users)
        holder.itemView.setOnClickListener {
            itemInterface.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = userList.size

    fun setData(user: List<UserDetails>) {
        this.userList = user
        notifyDataSetChanged()
    }


    class MyViewHolder(private val itemBinding: ItemUserDetailsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(users: UserDetails) {
            itemBinding.userFirstName.text = users.firstName
            itemBinding.userLastName.text = users.lastName
            itemBinding.userEmail.text = users.email
        }
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
}