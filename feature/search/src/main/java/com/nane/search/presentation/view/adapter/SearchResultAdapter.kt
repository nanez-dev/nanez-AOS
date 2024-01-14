package com.nane.search.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.search.databinding.SearchPerfumeItemViewBinding
import com.nane.search.databinding.SearchRecommendedSearchWordListItemViewBinding
import com.nane.search.presentation.data.SearchResultViewData
import com.nane.search.presentation.data.SearchViewType
import com.nane.search.presentation.view.AbsSearchResultViewHolder
import com.nane.search.presentation.view.RecommendedSearchWordListViewHolder
import com.nane.search.presentation.view.SearchPerfumeViewHolder

class SearchResultAdapter: RecyclerView.Adapter<AbsSearchResultViewHolder<*>>() {

    private var itemList: List<SearchResultViewData> = listOf()
    fun setItemList(list: List<SearchResultViewData>) {
        itemList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsSearchResultViewHolder<*> {

        return when(viewType) {
            SearchViewType.RECOMMENDATION_TYPE -> RecommendedSearchWordListViewHolder(SearchRecommendedSearchWordListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> SearchPerfumeViewHolder(SearchPerfumeItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) SearchViewType.RECOMMENDATION_TYPE else SearchViewType.PERFUME_TYPE
    }

    override fun onBindViewHolder(holder: AbsSearchResultViewHolder<*>, position: Int) {
        when (holder) {
            is RecommendedSearchWordListViewHolder -> holder.onBind(itemList.getOrNull(position) as SearchResultViewData.RecommendedSearchWordListItemViewData)
            is SearchPerfumeViewHolder -> holder.onBind(itemList.getOrNull(position) as SearchResultViewData.SearchPerfumeViewData)
        }
    }
}