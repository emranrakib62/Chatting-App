package com.example.chatapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ItemUserFrBinding

class UserAdapter(var user: UserAdapter.UserListaner): ListAdapter<User, UserAdapter.UserViewHolder>(comparator)
{
interface  UserListaner{
    fun moveuser(user:User)
}
    class  UserViewHolder(var binding: ItemUserFrBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        return UserViewHolder(ItemUserFrBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        getItem(position).let {
            holder.binding.nametv.text=it.name
            holder.binding.mobiletv.text=it.phone
holder.itemView.setOnClickListener {_->
user.moveuser(it)
}



        }
    }
companion object{


    var comparator = object :DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.email==newItem.email
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
           return oldItem==newItem
        }

    }
}



}
