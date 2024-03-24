package com.nane.storage.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.storage.BR
import com.nane.storage.databinding.WishListRecyclerviewBinding
import com.nane.storage.presentation.data.StorageViewData

class WishListAdapter : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {

    private var dataList : List<StorageViewData.StorageItem> = emptyList()

    var userActionListener: UserActionListener? = null
    interface UserActionListener {
        fun onMoveDetail(perfumeId: Int)
    }

    fun setItemList(list: List<StorageViewData.StorageItem>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(WishListRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList.getOrNull(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = dataList.size



    inner class ViewHolder(private val binding: WishListRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StorageViewData.StorageItem) {
            binding.apply {
                setVariable(BR.viewData, item)
                executePendingBindings()

                vgParent.setOnClickListener {
                    userActionListener?.onMoveDetail(item.id)
                }
            }
        }
    }
}