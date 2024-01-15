package com.nane.storage.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nane.storage.R
import com.nane.storage.BR
import com.nane.storage.databinding.WishlistRecyclerviewBinding
import com.nane.storage.presentation.data.StorageViewData

class WishListAdapter : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {

    private var dataList : List<StorageViewData.StorageItem> = emptyList()

    fun setItemList(list: List<StorageViewData.StorageItem>) {
        dataList = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: WishlistRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StorageViewData.StorageItem) {
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
        val item = dataList.getOrNull(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = dataList.size
}