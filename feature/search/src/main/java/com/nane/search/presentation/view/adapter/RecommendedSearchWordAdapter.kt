package com.nane.search.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.search.databinding.SearchResultItemViewBinding
import com.nane.search.presentation.data.SearchResultItemViewData

class RecommendedSearchWordAdapter: RecyclerView.Adapter<RecommendedSearchWordAdapter.SearchResultViewHolder>() {

    private var itemList: List<SearchResultItemViewData> = emptyList()

    fun setItemList(list: List<SearchResultItemViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(SearchResultItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
    }

    inner class SearchResultViewHolder(private val binding: SearchResultItemViewBinding): ViewHolder(binding.root) {

    }

    private var onItemClickListener: ItemClickListener? = null
    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        onItemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(idx: Int)
    }
}