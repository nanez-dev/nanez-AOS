package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.theme.BR
import com.nane.theme.databinding.ThemeAllAccordItemViewBinding
import com.nane.theme.presentation.data.AccordViewData

class AllAccordsAdapter(val onItemClick: () -> Unit): Adapter<AllAccordsAdapter.AllAccordViewHolder>() {

    private var itemList: List<AccordViewData> = emptyList()

    fun setItemList(list: List<AccordViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAccordViewHolder {
        return AllAccordViewHolder(ThemeAllAccordItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: AllAccordViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    inner class AllAccordViewHolder(private val binding: ThemeAllAccordItemViewBinding): ViewHolder(binding.root) {

        fun onBind(data: AccordViewData) {
            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }

        init {

        }
    }
}