package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.theme.databinding.ThemePopularAccordItemViewBinding
import com.nane.theme.presentation.data.AccordViewData

class PopularAccordsAdapter : Adapter<PopularAccordsAdapter.PopularAccordViewHolder>() {

    private var itemList: List<AccordViewData> = emptyList()

    fun setItemList(list: List<AccordViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAccordViewHolder {
        return PopularAccordViewHolder(ThemePopularAccordItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: PopularAccordViewHolder, position: Int) {
    }

    inner class PopularAccordViewHolder(private val binding: ThemePopularAccordItemViewBinding): ViewHolder(binding.root) {

    }

    private var onItemClickListener: ItemClickListener? = null
    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        onItemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick()
    }
}