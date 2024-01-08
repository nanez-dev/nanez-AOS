package com.nane.search.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.search.databinding.SearchNoResultItemViewBinding
import com.nane.search.databinding.SearchPerfumeListItemViewBinding
import com.nane.search.databinding.SearchRecommendationListItemViewBinding
import com.nane.search.presentation.data.SearchViewData
import com.nane.search.presentation.data.SearchViewType
import com.nane.search.presentation.view.AbsSearchViewHolder
import com.nane.search.presentation.view.NoResultViewHolder
import com.nane.search.presentation.view.RecommendationListViewHolder
import com.nane.search.presentation.view.SearchPerfumeListViewHolder

class SearchResultsAdapter: RecyclerView.Adapter<AbsSearchViewHolder<*>>() {

    private var itemList: List<SearchViewData> = emptyList()

    fun setItemList(list: List<SearchViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsSearchViewHolder<*> {
        return when (viewType) {
            SearchViewType.SEARCH_PERFUMES -> {
                SearchPerfumeListViewHolder(SearchPerfumeListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            SearchViewType.SEARCH_RECOMMENDATIONS -> {
                RecommendationListViewHolder(SearchRecommendationListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> {
                NoResultViewHolder(SearchNoResultItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return itemList[position].viewType
    }

    override fun onBindViewHolder(holder: AbsSearchViewHolder<*>, position: Int) {
        when(holder) {
            is RecommendationListViewHolder -> {
                (itemList[position] as? SearchViewData.RecommendationListViewType)?.let {
                    holder.onBind(it)
                }
            }
            is SearchPerfumeListViewHolder -> {
                (itemList[position] as? SearchViewData.SearchPerfumeListViewType)?.let {
                    holder.onBind(it)
                }
            }
            is NoResultViewHolder -> {
                (itemList[position] as? SearchViewData.NoResultsViewType)?.let {
                    holder.onBind(it)
                }
            }
        }
    }
}