package com.nane.home.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.home.BR
import com.nane.home.databinding.HomeRecommendPerfumeItemViewBinding
import com.nane.home.presentation.data.PerfumeItemViewData

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeRecommendPerfumeAdapter : RecyclerView.Adapter<HomeRecommendPerfumeAdapter.HomeRecommendPerfumeItemViewHolder>() {

    private var itemList: List<PerfumeItemViewData> = emptyList()
    var onItemClick: ((PerfumeItemViewData?) -> Unit)? = null

    fun setItemList(list: List<PerfumeItemViewData>) {
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

        private var data: PerfumeItemViewData? = null

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }

        fun onBind(data: PerfumeItemViewData) {
            this.data = data
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }
    }
}

