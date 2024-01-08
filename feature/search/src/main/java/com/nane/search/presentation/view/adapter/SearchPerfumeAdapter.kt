package com.nane.search.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.search.databinding.SearchPerfumeItemViewBinding
import com.nane.search.presentation.data.SearchViewData
import com.nane.theme.BR

class SearchPerfumeAdapter: RecyclerView.Adapter<SearchPerfumeAdapter.SearchPerfumeViewHolder>() {

    private var itemList: List<SearchViewData.SearchPerfumeListViewType.SearchPerfumeViewData> = emptyList()

    fun setItemList(item: SearchViewData.SearchPerfumeListViewType) {
        itemList = item.list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPerfumeViewHolder {
        return SearchPerfumeViewHolder(SearchPerfumeItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: SearchPerfumeViewHolder, position: Int) {
        holder.onBind(itemList.getOrNull(position))
    }

    inner class SearchPerfumeViewHolder(private val binding: SearchPerfumeItemViewBinding): ViewHolder(binding.root) {
        fun onBind(data: SearchViewData.SearchPerfumeListViewType.SearchPerfumeViewData?) {
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