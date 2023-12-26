package com.nane.search.presentation.view

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.search.R
import com.nane.search.databinding.SearchResultFragmentBinding
import com.nane.search.presentation.view.adapter.RecommendedSearchWordAdapter
import com.nane.search.presentation.view.adapter.SearchResultsAdapter
import com.nane.search.presentation.view.adapter.decoration.RecommendedSearchWordItemDecoration
import com.nane.search.presentation.view.adapter.decoration.SearchResultItemDecoration
import com.nane.search.presentation.viewmodel.SearchViewModel

class SearchResultFragment: BaseBindFragment<SearchResultFragmentBinding, SearchViewModel>(R.layout.search_result_fragment) {

    override fun createViewModel(): SearchViewModel = viewModels<SearchViewModel>().value

    override fun initFragment(
        dataBinding: SearchResultFragmentBinding,
        viewModel: SearchViewModel
    ) {
        with(dataBinding.rvRecommendedSearchWord) {
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

        with(dataBinding.rvSearchResult) {
            adapter ?: SearchResultsAdapter().apply { adapter = this }
            layoutManager ?: GridLayoutManager(this.context, 2).apply { layoutManager = this }

            if (itemDecorationCount == 0) addItemDecoration(SearchResultItemDecoration())
            (adapter as RecommendedSearchWordAdapter).setOnItemClickListener(
                object: RecommendedSearchWordAdapter.ItemClickListener {
                    override fun onItemClick(idx: Int) {
                        //
                    }
                }
            )
        }

        viewModel.recommendedSearchWords.observe(viewLifecycleOwner) {
            (dataBinding.rvRecommendedSearchWord.adapter as? RecommendedSearchWordAdapter)?.setItemList(it)
        }

        viewModel.searchResult.observe(viewLifecycleOwner) {
            (dataBinding.rvSearchResult.adapter as? SearchResultsAdapter)?.setItemList(it)
        }
    }
}