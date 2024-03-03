package com.nane.search.presentation.view

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.search.R
import com.nane.search.databinding.SearchActivityBinding
import com.nane.search.presentation.data.SearchResultViewData
import com.nane.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.addFragment


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
        val noResultFragment = SearchNoResultFragment()

        dataBinding.apply {
            if (supportFragmentManager.fragments.isEmpty()) {
                addFragment(
                    container = this.searchResultsContainer,
                    saveInstanceState = null,
                    tag = TAG_NO_RESULT,
                    arguments = null,
                    isBackStackEnabled = false
                ) {
                    noResultFragment
                }
            }


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
                    hideImeServiceFrom(editSearch)
                }
            }

            viewModel.searchResults.observe(this@SearchActivity) { results ->
                val havePerfumeResult = results
                    .filterIsInstance<SearchResultViewData.SearchPerfumeViewData>()
                    .isNotEmpty()

                if (havePerfumeResult) { // 검색결과 있을 경우 Fragment Inflate
                    if (supportFragmentManager.fragments.any { it.tag == TAG_RESULT }) return@observe
                    addFragment(
                        container = this.searchResultsContainer,
                        saveInstanceState = null,
                        tag = TAG_RESULT,
                        arguments = null,
                        isBackStackEnabled = false
                    ) {
                        SearchResultsFragment()
                    }
                } else { // 검색결과 없을 경우 Fragment Inflate
                    if (supportFragmentManager.fragments.any { it.tag == TAG_NO_RESULT }) return@observe
                    addFragment(
                        container = this.searchResultsContainer,
                        saveInstanceState = null,
                        tag = TAG_NO_RESULT,
                        arguments = null,
                        isBackStackEnabled = false
                    ) {
                        noResultFragment
                    }
                }
            }
        }
    }

    private fun hideImeServiceFrom(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {

        private const val TAG_EMPTY = "EMPTY"
        private const val TAG_RESULT = "RESULT"
        private const val TAG_NO_RESULT = "NO_RESULT"

        fun createIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }
}