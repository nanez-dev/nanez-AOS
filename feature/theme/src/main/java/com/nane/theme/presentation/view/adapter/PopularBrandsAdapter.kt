package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.theme.BR
import com.nane.theme.databinding.ThemePopularBrandItemViewBinding
import com.nane.theme.presentation.data.BrandItemViewData

class PopularBrandsAdapter : Adapter<PopularBrandsAdapter.PopularBrandViewHolder>() {

    private var itemList: List<BrandItemViewData> = emptyList()

    fun setItemList(list: List<BrandItemViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularBrandViewHolder {
        return PopularBrandViewHolder(ThemePopularBrandItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: PopularBrandViewHolder, position: Int) {
        holder.onBind(itemList.getOrNull(position))
    }

    inner class PopularBrandViewHolder(private val binding: ThemePopularBrandItemViewBinding): ViewHolder(binding.root) {

        fun onBind(data: BrandItemViewData?) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(adapterPosition)
            }
        }
    }

    private var onItemClickListener: ItemClickListener? = null
    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        onItemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(idx: Int)
    }
}