package com.nane.search.presentation.view

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nane.search.BR
import com.nane.search.databinding.SearchNoResultItemViewBinding
import com.nane.search.databinding.SearchPerfumeListItemViewBinding
import com.nane.search.databinding.SearchRecommendationListItemViewBinding
import com.nane.search.presentation.data.SearchViewData
import com.nane.search.presentation.view.adapter.RecommendedSearchWordAdapter
import com.nane.search.presentation.view.adapter.SearchPerfumeAdapter
import com.nane.search.presentation.view.adapter.decoration.RecommendedSearchWordItemDecoration
import com.nane.search.presentation.view.adapter.decoration.SearchPerfumeItemDecoration

class RecommendationListViewHolder(
    private val binding: SearchRecommendationListItemViewBinding
): AbsSearchViewHolder<SearchViewData.RecommendationListViewType>(binding.root) {
    override fun onBind(data: SearchViewData.RecommendationListViewType) {
        (binding.rvRecommendedSearchWords.adapter as RecommendedSearchWordAdapter).setItemList(data)
    }

    init {
        with(binding.rvRecommendedSearchWords) {
            adapter ?: RecommendedSearchWordAdapter().apply { adapter = this }
            layoutManager ?: LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false).apply { layoutManager = this }

            if (itemDecorationCount == 0) addItemDecoration(RecommendedSearchWordItemDecoration())
            (adapter as RecommendedSearchWordAdapter).setOnItemClickListener(
                object: RecommendedSearchWordAdapter.ItemClickListener {
                    override fun onItemClick(idx: Int) {
                        //
                    }
                }
            )
        }
    }
}

class SearchPerfumeListViewHolder(
    private val binding: SearchPerfumeListItemViewBinding
): AbsSearchViewHolder<SearchViewData.SearchPerfumeListViewType>(binding.root) {
    override fun onBind(data: SearchViewData.SearchPerfumeListViewType) {
        (binding.rvSearchPerfumes.adapter as SearchPerfumeAdapter).setItemList(data)
    }

    init {
        with(binding.rvSearchPerfumes) {
            adapter ?: SearchPerfumeAdapter().apply { adapter = this }
            layoutManager ?: GridLayoutManager(this.context, 2).apply { layoutManager = this }

            if (itemDecorationCount == 0) addItemDecoration(SearchPerfumeItemDecoration())
            (adapter as SearchPerfumeAdapter).setOnItemClickListener(
                object: SearchPerfumeAdapter.ItemClickListener {
                    override fun onItemClick(idx: Int) {
                        //
                    }
                }
            )
        }
    }
}

class NoResultViewHolder(
    private val binding: SearchNoResultItemViewBinding
): AbsSearchViewHolder<SearchViewData.NoResultsViewType>(binding.root) {
    override fun onBind(data: SearchViewData.NoResultsViewType) {
        binding.setVariable(BR.viewData, data)
        binding.executePendingBindings()
    }
}

abstract class AbsSearchViewHolder<T : SearchViewData>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(data: T)
}