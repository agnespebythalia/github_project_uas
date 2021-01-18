package com.github.api.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.api.R
import com.github.api.data.User
import com.github.api.databinding.ActivityMainBinding
import com.github.api.databinding.ItemMainBinding
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val list = ArrayList<User>()

    private var onItemClickCallback : OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback

    }

    fun setList(user: ArrayList<User>){
        list.clear()
        list.addAll(user)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user : User) {
            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(user)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(itemAvatar)
                itemLogin.text = user.login
                itemDescription.text = user.html_url
            }

            with(itemView){

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data : User)
    }
}