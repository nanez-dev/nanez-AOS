package com.nane.storage.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nane.network.api.storage.StorageApi
import com.nane.storage.R
import com.nane.storage.BR
import com.nane.storage.databinding.WishlistRecyclerviewBinding

class StorageWishlistAdapter : RecyclerView.Adapter<StorageWishlistAdapter.ViewHolder>() {

    private var dataSet : List<StorageApi.Response> = emptyList()

    fun setItemList(list: List<StorageApi.Response>) {
        dataSet = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: WishlistRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StorageApi.Response) {
            binding.apply {
                setVariable(BR.viewData, item)
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: WishlistRecyclerviewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.wishlist_recyclerview,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataSet.size
}