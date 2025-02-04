package com.ext.paginationpractice1.paginationAPIwithImage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ext.paginationpractice1.R
import com.ext.paginationpractice1.databinding.RawPaginationApiBinding
import com.ext.paginationpractice1.databinding.RawPaginationApiWithImageBinding
import com.ext.paginationpractice1.util.Pagination1
import com.ext.paginationpractice1.util.User
import com.ext.paginationpractice1.util.UserResponse

class AdapterPaginationWithImage(val requireContext: Context, val dataList: ArrayList<User>) : RecyclerView.Adapter<AdapterPaginationWithImage.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPaginationWithImage.ViewHolder {
        val binding = RawPaginationApiWithImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterPaginationWithImage.ViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.apply {
            tvId.text = data.first_name
            tvFirstName.text = data.last_name
            tvLastName.text = data.email

            Glide.with(requireContext)
                .load(data.avatar)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imgUser)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(val binding: RawPaginationApiWithImageBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}