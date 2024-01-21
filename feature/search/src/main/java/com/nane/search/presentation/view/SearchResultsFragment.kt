package com.nane.search.presentation.view

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.search.R
import com.nane.search.databinding.SearchResultsFragmentBinding
import com.nane.search.presentation.data.SearchResultViewData
import com.nane.search.presentation.data.SearchViewType
import com.nane.search.presentation.view.adapter.SearchResultAdapter
import com.nane.search.presentation.view.adapter.decoration.SearchResultItemDecoration
import com.nane.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchResultsFragment : BaseBindFragment<SearchResultsFragmentBinding, SearchViewModel>(R.layout.search_results_fragment) {

    private val _viewModel: SearchViewModel by activityViewModels()

    override fun createViewModel(): SearchViewModel = _viewModel

    override fun initFragment(
        dataBinding: SearchResultsFragmentBinding,
        viewModel: SearchViewModel
    ) {
        val onSearchResultClickListener = object: SearchResultAdapter.SearchResultClickListener {
            override fun onSearchWordClick(idx: Int) {

            }

            override fun onPerfumeClick(idx: Int) {

            }
        }
        dataBinding.apply {
            with(dataBinding.rvSearchResults) {
                adapter ?: SearchResultAdapter().apply { adapter = this }
                (adapter as SearchResultAdapter).setOnSearchResultClickListener(onSearchResultClickListener)
                layoutManager ?: GridLayoutManager(this.context, 2).apply {
                    spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return when ((adapter as SearchResultAdapter).getItemViewType(position)) {
                                SearchViewType.RECOMMENDATION_TYPE -> 2
                                else -> 1
                            }
                        }
                    }
                    layoutManager = this
                }
                if (itemDecorationCount == 0) addItemDecoration(SearchResultItemDecoration())
            }
        }

        viewModel.searchResults.observe(viewLifecycleOwner) {
            (dataBinding.rvSearchResults.adapter as SearchResultAdapter).setItemList(it)
        }
    }
}