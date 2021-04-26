package com.example.userprofiles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userprofiles.user_data.UserInfo
import com.example.userprofiles.user_data.Users


class CellAdapter(context: Context, private val clickListener: (UserInfo) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val userList = mutableListOf<UserInfo>()

    protected class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img: ImageView = itemView.findViewById(R.id.user_image)
        var userName: TextView = itemView.findViewById(R.id.user_name)
        var card: CardView = itemView.findViewById(R.id.cell_id)
    }

    override fun getItemCount(): Int = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_view, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.userName.text = userList[position].first_name

        // Using Glide Lib to set image
        Glide.with(viewHolder.img.context)
            .load(userList[position].avatar)
            .into(viewHolder.img)

        viewHolder.itemView.setOnClickListener {
            clickListener(userList[position])
        }
    }

    fun addAll(list: List<UserInfo>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

}