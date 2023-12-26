package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.theme.BR
import com.nane.theme.databinding.ThemePopularAccordItemViewBinding
import com.nane.theme.presentation.data.AccordItemViewData
import com.nane.theme.presentation.data.AccordViewData
import org.techtown.nanez.utils.util.toFirstUpperCase

class PopularAccordsAdapter : Adapter<PopularAccordsAdapter.PopularAccordViewHolder>() {

    private var itemList: List<AccordItemViewData> = emptyList()

    fun setItemList(list: List<AccordItemViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAccordViewHolder {
        return PopularAccordViewHolder(ThemePopularAccordItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: PopularAccordViewHolder, position: Int) {
        holder.onBind(itemList.getOrNull(position))
    }

    inner class PopularAccordViewHolder(private val binding: ThemePopularAccordItemViewBinding): ViewHolder(binding.root) {
        fun onBind(data: AccordItemViewData?) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
            if (data == null) return
            binding.txtAccordTitleEnglish.text = data.engName?.toFirstUpperCase()
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