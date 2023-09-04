package org.techtown.nanez.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nanez.BR
import org.techtown.nanez.databinding.HomeHorizontalItemViewBinding
import org.techtown.nanez.home.data.HomePerfumeItemViewData

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeHorizontalAdapter : RecyclerView.Adapter<HomeHorizontalAdapter.HomeHorizontalViewHolder>() {

    private var itemList: List<HomePerfumeItemViewData> = emptyList()

    fun setItemList(list: List<HomePerfumeItemViewData>) {
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

        init {
            binding.vgParent.setOnClickListener {  }
        }

        fun onBind(data: HomePerfumeItemViewData) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }
    }
}
