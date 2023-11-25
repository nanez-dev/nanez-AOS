package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.theme.BR
import com.nane.theme.databinding.ThemeAllBrandItemViewBinding
import com.nane.theme.databinding.ThemeRelatedBrandItemViewBinding
import com.nane.theme.presentation.data.BrandPerfumeViewData
import com.nane.theme.presentation.data.BrandViewData
import org.techtown.nanez.utils.util.toPrice
import java.text.DecimalFormat

class RelatedBrandPerfumesAdapter(): Adapter<RelatedBrandPerfumesAdapter.BrandViewHolder>() {

    private var itemList: List<BrandPerfumeViewData> = emptyList()

    fun setItemList(list: List<BrandPerfumeViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(ThemeRelatedBrandItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.onBind(itemList.getOrNull(position))
    }

    inner class BrandViewHolder(private val binding: ThemeRelatedBrandItemViewBinding): ViewHolder(binding.root) {

        fun onBind(data: BrandPerfumeViewData?) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
            if (data == null) return
            binding.txtItemPrice.text = data.price.toPrice()
        }

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(itemList.getOrNull(adapterPosition)?.id ?: -1)
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