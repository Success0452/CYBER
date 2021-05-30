package com.famous.cyber

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv.view.*

class DataAdapter(var list: ArrayList<DatabaseModel>): RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userId = itemView.UserIdText
        val userName = itemView.userNameText
        val computerName = itemView.computerNameText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = list[position].userId
        holder.userName.text = list[position].userName
        holder.computerName.text = list[position].computerName
    }

    override fun getItemCount(): Int {
       return list.size
    }
}