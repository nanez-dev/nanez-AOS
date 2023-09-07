package com.nane.home.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.home.BR
import com.nane.home.databinding.HomeBrandItemViewBinding
import com.nane.home.presentation.data.HomeViewData

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeBrandAdapter : RecyclerView.Adapter<HomeBrandAdapter.HomeBrandItemViewHolder>() {

    private var itemList: List<HomeViewData.HomeBrandData.HomeBrandItemData> = emptyList()

    fun setItemList(list: List<HomeViewData.HomeBrandData.HomeBrandItemData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBrandItemViewHolder {
        return HomeBrandItemViewHolder(HomeBrandItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeBrandItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    inner class HomeBrandItemViewHolder(private val binding: HomeBrandItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HomeViewData.HomeBrandData.HomeBrandItemData) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }
    }
}

