package com.nane.home.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.home.BR
import com.nane.home.databinding.HomeHorizontalItemViewBinding
import com.nane.home.presentation.data.PerfumeItemViewData

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeHorizontalAdapter : RecyclerView.Adapter<HomeHorizontalAdapter.HomeHorizontalViewHolder>() {

    private var itemList: List<PerfumeItemViewData> = emptyList()
    var onItemClick: ((PerfumeItemViewData?) -> Unit)? = null

    fun setItemList(list: List<PerfumeItemViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHorizontalViewHolder {
        return HomeHorizontalViewHolder(HomeHorizontalItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeHorizontalViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount() = itemList.size


    inner class HomeHorizontalViewHolder(private val binding: HomeHorizontalItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private var data: PerfumeItemViewData? = null

        init {
            binding.vgParent.setOnClickListener {
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
