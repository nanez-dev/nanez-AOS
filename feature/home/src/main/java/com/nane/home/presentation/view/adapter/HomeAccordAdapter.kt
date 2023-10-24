package com.nane.home.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.home.BR
import com.nane.home.databinding.HomeAccordItemViewBinding
import com.nane.home.presentation.data.HomeViewData

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeAccordAdapter : RecyclerView.Adapter<HomeAccordAdapter.HomeAccordItemViewHolder>() {

    private var itemList: List<HomeViewData.Accord.AccordItem> = emptyList()

    fun setItemList(list: List<HomeViewData.Accord.AccordItem>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAccordItemViewHolder {
        return HomeAccordItemViewHolder(HomeAccordItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeAccordItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    inner class HomeAccordItemViewHolder(private val binding: HomeAccordItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HomeViewData.Accord.AccordItem) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }
    }
}

