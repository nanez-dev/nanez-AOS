package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.theme.BR
import com.nane.theme.databinding.ThemeAllBrandItemViewBinding
import com.nane.theme.presentation.data.BrandViewData

class AllBrandsAdapter(val onItemClick: () -> Unit): Adapter<AllBrandsAdapter.BrandViewHolder>() {

    private var itemList: List<BrandViewData> = emptyList()

    fun setItemList(list: List<BrandViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(ThemeAllBrandItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    inner class BrandViewHolder(private val binding: ThemeAllBrandItemViewBinding): ViewHolder(binding.root) {

        fun onBind(data: BrandViewData) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }

        init {
            binding.imgTheme.setOnClickListener {
                onItemClick.invoke()
            }
        }
    }
}