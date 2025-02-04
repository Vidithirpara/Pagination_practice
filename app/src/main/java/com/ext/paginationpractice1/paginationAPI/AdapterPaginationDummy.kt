package com.ext.paginationpractice1.paginationAPI

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ext.paginationpractice1.databinding.RawPaginationApiBinding
import com.ext.paginationpractice1.util.Pagination1

class AdapterPaginationDummy(val requireContext: Context, val dataList: ArrayList<Pagination1>) : RecyclerView.Adapter<AdapterPaginationDummy.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPaginationDummy.ViewHolder {
        val binding = RawPaginationApiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterPaginationDummy.ViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.apply {
            tvTitle.text = data.title
            tvBody.text = data.body
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(val binding: RawPaginationApiBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}