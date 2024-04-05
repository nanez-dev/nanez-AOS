package com.nane.search.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.nane.base.view.BaseBindFragment
import com.nane.detail.presentation.view.PerfumeDetailActivity
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

    private val onSearchResultClickListener by lazy {
        object: SearchResultAdapter.SearchResultClickListener {
            override fun onSearchWordClick(keyword: String?) {
                activity?.let {
                    it.startActivity(SearchActivity.createIntent(it, keyword))
                }
            }

            override fun onPerfumeClick(itemId: Int) {
                activity?.let {
                    it.startActivity(PerfumeDetailActivity.createIntent(it, itemId))
                }
            }
        }
    }


    override fun createViewModel(): SearchViewModel = _viewModel

    override fun initFragment(dataBinding: SearchResultsFragmentBinding, viewModel: SearchViewModel) {
        dataBinding.apply {
            with(dataBinding.rvSearchResults) {
                adapter ?: SearchResultAdapter().apply {
                    setOnSearchResultClickListener(onSearchResultClickListener)
                    adapter = this
                }
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
                addOnScrollListener(
                    object: OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            if (dy <= 0) return

                            val lastItemPosition = (layoutManager as? GridLayoutManager)?.findLastVisibleItemPosition() ?: Int.MIN_VALUE
                            val totalItemCount = (adapter as? SearchResultAdapter)?.itemCount ?: 0
                            if (lastItemPosition >= totalItemCount - VISIBLE_LAST_ITEMS_COUNT) {
                                viewModel.loadMorePerfumes()
                            }
                        }
                    }
                )
            }
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            val havePerfumeResult = results
                .filterIsInstance<SearchResultViewData.SearchPerfumeViewData>()
                .isNotEmpty()

            if (havePerfumeResult) {
                dataBinding.rvSearchResults.visibility = View.VISIBLE
                dataBinding.vgNoResult.visibility = View.GONE
                (dataBinding.rvSearchResults.adapter as? SearchResultAdapter)?.setItemList(results)
            } else {
                dataBinding.rvSearchResults.visibility = View.GONE
                dataBinding.vgNoResult.visibility = View.VISIBLE
            }
        }

        viewModel.searchWord.observe(viewLifecycleOwner) {
            (dataBinding.rvSearchResults.adapter as? SearchResultAdapter)?.clearItemList()
        }
    }

    companion object {
        private const val VISIBLE_LAST_ITEMS_COUNT = 2
    }
}
