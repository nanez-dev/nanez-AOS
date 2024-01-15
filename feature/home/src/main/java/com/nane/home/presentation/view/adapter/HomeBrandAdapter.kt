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

    private var itemList: List<HomeViewData.Brand.BrandItem> = emptyList()
    var onItemClick: ((Int) -> Unit)? = null

    fun setItemList(list: List<HomeViewData.Brand.BrandItem>) {
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
        private var brandId = -1

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(brandId)
            }
        }

        fun onBind(data: HomeViewData.Brand.BrandItem) {
            brandId = data.id
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }
    }
}

