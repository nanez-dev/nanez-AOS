package com.nane.storage.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.storage.BR
import com.nane.storage.databinding.HavingListRecyclerviewBinding
import com.nane.storage.presentation.data.StorageViewData

class HavingListAdapter : RecyclerView.Adapter<HavingListAdapter.ViewHolder>() {

    private var dataList : List<StorageViewData.StorageItem> = emptyList()

    fun setItemList(list: List<StorageViewData.StorageItem>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HavingListRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList.getOrNull(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = dataList.size



    inner class ViewHolder(private val binding: HavingListRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StorageViewData.StorageItem) {
            binding.apply {
                setVariable(BR.viewData, item)
                executePendingBindings()
            }
        }
    }
}