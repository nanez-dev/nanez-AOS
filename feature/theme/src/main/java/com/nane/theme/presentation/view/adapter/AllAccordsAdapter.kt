package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.theme.BR
import com.nane.theme.databinding.ThemeAllAccordItemViewBinding
import com.nane.theme.presentation.data.AccordItemViewData
import com.nane.theme.presentation.data.AccordViewData

class AllAccordsAdapter : Adapter<AllAccordsAdapter.AllAccordViewHolder>() {

    private var itemList: List<AccordItemViewData> = emptyList()

    fun setItemList(list: List<AccordItemViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAccordViewHolder {
        return AllAccordViewHolder(ThemeAllAccordItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: AllAccordViewHolder, position: Int) {
        holder.onBind(itemList.getOrNull(position))
    }

    inner class AllAccordViewHolder(private val binding: ThemeAllAccordItemViewBinding): ViewHolder(binding.root) {

        fun onBind(data: AccordItemViewData?) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
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