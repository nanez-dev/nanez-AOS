package com.nane.search.presentation.view

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindActivity
import com.nane.search.R
import com.nane.search.databinding.SearchActivityBinding
import com.nane.search.presentation.data.SearchViewData
import com.nane.search.presentation.view.adapter.SearchResultsAdapter
import com.nane.search.presentation.view.adapter.decoration.SearchResultItemDecoration
import com.nane.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseBindActivity<SearchActivityBinding, SearchViewModel>(R.layout.search_activity) {

    override fun createViewModel(): SearchViewModel = viewModels<SearchViewModel>().value

    override fun onActionBackPressed() {
        finish()
    }

    override fun initActivity(
        dataBinding: SearchActivityBinding,
        viewModel: SearchViewModel
    ) {
        dataBinding.apply {

            with(btnBack) {
                setOnClickListener {
                    finish()
                }
            }

            with(editSearch) {
                requestFocus()
                setOnKeyListener { _, keyCode, event ->
                    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        btnSearch.performClick()
                        true
                    } else false
                }
            }

            with(btnSearch) {
                setOnClickListener {
                    val currentSearchWord = editSearch.text.toString()
                    
                    // 아무것도 검색하지 않은 경우, 검색어가 이전과 같은 경우 API 호출 무시
                    if (currentSearchWord.isEmpty() || currentSearchWord == viewModel.searchWord) return@setOnClickListener
                    if (!editSearch.isFocused) {
                        editSearch.isFocusable = true
                        return@setOnClickListener
                    }

                    viewModel.searchWith(currentSearchWord)
                }
            }

            with(dataBinding.rvSearchResults) {
                adapter ?: SearchResultsAdapter().apply { adapter = this }
                layoutManager ?: LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false).apply { layoutManager = this }

                if (itemDecorationCount == 0) addItemDecoration(SearchResultItemDecoration())
            }

            viewModel.recommendedSearchWords.observe(this@SearchActivity) {

            }

            viewModel.searchResult.observe(this@SearchActivity) {
                val result = if (it.list.isEmpty()) {
                    listOf(SearchViewData.NoResultsViewType)
                } else {
                    listOf(viewModel.recommendedSearchWords.value ?: SearchViewData.RecommendationListViewType(), it)
                }
                (dataBinding.rvSearchResults.adapter as SearchResultsAdapter)
                    .setItemList(result)
            }
        }
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }
}