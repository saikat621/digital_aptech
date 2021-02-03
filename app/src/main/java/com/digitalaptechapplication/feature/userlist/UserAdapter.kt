package com.digitalaptechapplication.feature.userlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.digitalaptechapplication.R
import com.digitalaptechapplication.database.table.UserEntity
import com.digitalaptechapplication.databinding.InflateUserItemBinding
import kotlinx.android.synthetic.main.inflate_user_item.view.*

class UserAdapter(private val list: List<UserEntity>?) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: InflateUserItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.inflate_user_item, parent, false)
        return ViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinding.user = list?.get(position)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    inner class ViewHolder(binding: InflateUserItemBinding, adapter: UserAdapter) : RecyclerView.ViewHolder(binding.root) {
        val dataBinding: InflateUserItemBinding = binding
    }
}