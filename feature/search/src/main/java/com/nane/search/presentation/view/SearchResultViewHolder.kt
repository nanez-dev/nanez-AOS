package com.nane.search.presentation.view

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nane.search.databinding.SearchPerfumeItemViewBinding
import com.nane.search.databinding.SearchRecommendedSearchWordListItemViewBinding
import com.nane.search.presentation.data.SearchResultViewData
import com.nane.search.presentation.view.adapter.RecommendedSearchWordAdapter
import com.nane.search.presentation.view.adapter.decoration.RecommendedSearchWordItemDecoration
import com.nane.theme.BR

class RecommendedSearchWordListViewHolder(
    private val binding: SearchRecommendedSearchWordListItemViewBinding
): AbsSearchResultViewHolder<SearchResultViewData.RecommendedSearchWordListItemViewData>(binding.root) {
    override fun onBind(data: SearchResultViewData.RecommendedSearchWordListItemViewData) {
        with(binding.rvRecommendedSearchWords) {
            adapter ?: RecommendedSearchWordAdapter().apply { adapter = this }
            (adapter as RecommendedSearchWordAdapter).setItemList(data.wordList)
            layoutManager ?: LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false).apply { layoutManager = this }

            if (itemDecorationCount == 0) addItemDecoration(RecommendedSearchWordItemDecoration())
        }
    }
}

class SearchPerfumeViewHolder(
    private val binding: SearchPerfumeItemViewBinding
): AbsSearchResultViewHolder<SearchResultViewData.SearchPerfumeViewData>(binding.root) {
    override fun onBind(data: SearchResultViewData.SearchPerfumeViewData) {
        binding.setVariable(BR.viewData, data)
        binding.executePendingBindings()
    }
}

abstract class AbsSearchResultViewHolder<T: SearchResultViewData>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(data: T)
}