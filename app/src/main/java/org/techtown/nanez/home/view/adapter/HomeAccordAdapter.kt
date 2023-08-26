package org.techtown.nanez.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nanez.BR
import org.techtown.nanez.databinding.HomeAccordItemViewBinding
import org.techtown.nanez.home.data.HomeViewData

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeAccordAdapter : RecyclerView.Adapter<HomeAccordAdapter.HomeAccordItemViewHolder>() {

    private var itemList: List<HomeViewData.HomeAccordData.HomeAccordItemData> = emptyList()

    fun setItemList(list: List<HomeViewData.HomeAccordData.HomeAccordItemData>) {
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

        fun onBind(data: HomeViewData.HomeAccordData.HomeAccordItemData) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }
    }
}

