package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.theme.BR
import com.nane.theme.databinding.ThemePopularAccordItemViewBinding
import com.nane.theme.presentation.data.AccordItemViewData
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

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onPopularAccordItemClick(itemList.getOrNull(adapterPosition)?.id ?: -1)
            }
        }

        fun onBind(data: AccordItemViewData?) {
            data ?: return

            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
            binding.txtAccordTitleEnglish.text = data.engName?.toFirstUpperCase()
        }
    }

    private var onItemClickListener: AccordAdapter.AccordItemClickListener? = null
    fun setOnItemClickListener(itemClickListener: AccordAdapter.AccordItemClickListener?) {
        onItemClickListener = itemClickListener
    }
}