package com.example.retrofit_coroutines_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.myrow_item.view.*
import model.User

class MainAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<MainAdapter.DataViewHolder>() {



    class DataViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        fun bind(user: User){
            itemView.apply {
                textViewUserEmail.text =user.email
                textViewUserName.text = user.name
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.myrow_item,parent,false))


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun addUsers(users: ArrayList<User>){
        this.users.apply {
            clear()
            addAll(users)
        }
    }

}