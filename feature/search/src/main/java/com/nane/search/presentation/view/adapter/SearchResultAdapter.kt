package com.nane.search.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nane.search.BR
import com.nane.search.databinding.SearchPerfumeItemViewBinding
import com.nane.search.databinding.SearchRecommendedSearchWordListItemViewBinding
import com.nane.search.presentation.data.SearchResultViewData
import com.nane.search.presentation.data.SearchViewType
import com.nane.search.presentation.view.AbsSearchResultViewHolder
import com.nane.search.presentation.view.adapter.decoration.RecommendedSearchWordItemDecoration

class SearchResultAdapter: RecyclerView.Adapter<AbsSearchResultViewHolder<*>>() {

    private val itemList: MutableList<SearchResultViewData> = mutableListOf()

    private var onSearchResultClickListener: SearchResultClickListener? = null

    interface SearchResultClickListener {
        fun onSearchWordClick(keyword: String?)
        fun onPerfumeClick(itemId: Int)
    }

    fun setItemList(list: List<SearchResultViewData>) {
        val insertedPositionStart = itemCount
        itemList.addAll(list)
        notifyItemRangeInserted(insertedPositionStart, list.size)
    }

    fun clearItemList() {
        itemList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsSearchResultViewHolder<*> {

        return when(viewType) {
            SearchViewType.RECOMMENDATION_TYPE -> RecommendedSearchWordListViewHolder(SearchRecommendedSearchWordListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> SearchPerfumeViewHolder(SearchPerfumeItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return itemList.getOrNull(position)?.viewType ?: 0
    }

    override fun onBindViewHolder(holder: AbsSearchResultViewHolder<*>, position: Int) {
        when (holder) {
            is RecommendedSearchWordListViewHolder -> {
                holder.onBind(
                    data = itemList.getOrNull(position) as? SearchResultViewData.RecommendedSearchWordListItemViewData
                )
            }
            is SearchPerfumeViewHolder -> {
                holder.onBind(
                    data = itemList.getOrNull(position) as? SearchResultViewData.SearchPerfumeViewData
                )
            }
        }
    }


    fun setOnSearchResultClickListener(itemClickListener: SearchResultClickListener) {
        onSearchResultClickListener = itemClickListener
    }

    private inner class RecommendedSearchWordListViewHolder(
        private val binding: SearchRecommendedSearchWordListItemViewBinding
    ): AbsSearchResultViewHolder<SearchResultViewData.RecommendedSearchWordListItemViewData>(binding.root) {

        override fun onBind(data: SearchResultViewData.RecommendedSearchWordListItemViewData?) {
            data ?: return

            with(binding.rvRecommendedSearchWords) {
                adapter ?: RecommendedSearchWordAdapter().apply {
                    setItemList(data.wordList)
                    setOnItemClickListener(onSearchResultClickListener)
                    adapter = this
                }
                layoutManager ?: LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false).apply { layoutManager = this }

                if (itemDecorationCount == 0) addItemDecoration(RecommendedSearchWordItemDecoration())
            }
        }
    }

    private inner class SearchPerfumeViewHolder(
        private val binding: SearchPerfumeItemViewBinding
    ): AbsSearchResultViewHolder<SearchResultViewData.SearchPerfumeViewData>(binding.root) {

        init {
            binding.root.setOnClickListener {
                onSearchResultClickListener?.onPerfumeClick(
                    (itemList.getOrNull(adapterPosition) as? SearchResultViewData.SearchPerfumeViewData)?.id ?: -1
                )
            }
        }

        override fun onBind(data: SearchResultViewData.SearchPerfumeViewData?) {
            data ?: return

            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }
    }
}