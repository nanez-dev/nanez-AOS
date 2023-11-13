package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.theme.BR
import com.nane.theme.databinding.ThemePopularBrandItemViewBinding
import com.nane.theme.presentation.data.BrandViewData

class PopularBrandsAdapter(val onItemClick: () -> Unit): Adapter<PopularBrandsAdapter.PopularBrandViewHolder>() {

    private var itemList: List<BrandViewData> = emptyList()

    fun setItemList(list: List<BrandViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularBrandViewHolder {
        return PopularBrandViewHolder(ThemePopularBrandItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: PopularBrandViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    inner class PopularBrandViewHolder(private val binding: ThemePopularBrandItemViewBinding): ViewHolder(binding.root) {

        fun onBind(data: BrandViewData) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }

        init {

        }
    }
}