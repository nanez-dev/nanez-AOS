package com.nane.home.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.home.BR
import com.nane.home.databinding.HomeRecommendPerfumeItemViewBinding
import com.nane.home.presentation.data.HomePerfumeItemViewData

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeRecommendPerfumeAdapter : RecyclerView.Adapter<HomeRecommendPerfumeAdapter.HomeRecommendPerfumeItemViewHolder>() {

    private var itemList: List<HomePerfumeItemViewData> = emptyList()

    fun setItemList(list: List<HomePerfumeItemViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecommendPerfumeItemViewHolder {
        return HomeRecommendPerfumeItemViewHolder(HomeRecommendPerfumeItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeRecommendPerfumeItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount() = itemList.size


    inner class HomeRecommendPerfumeItemViewHolder(private val binding: HomeRecommendPerfumeItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HomePerfumeItemViewData) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }
    }
}

