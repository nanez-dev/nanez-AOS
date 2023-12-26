package com.nane.search.presentation.view

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.BaseBindFragment
import com.nane.search.R
import com.nane.search.databinding.SearchActivityBinding
import com.nane.search.presentation.viewmodel.SearchViewModel
import org.techtown.nanez.utils.util.addFragment

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

            with(btnSearch) {
                setOnClickListener {
                    if (editSearch.text.toString().isEmpty()) return@setOnClickListener
                    if (!editSearch.isFocused) {
                        editSearch.isFocusable = true
                        return@setOnClickListener
                    }

                    viewModel.searchWith(editSearch.text.toString())
                    if (supportFragmentManager.backStackEntryCount > 1) return@setOnClickListener

                    addFragment(
                        container = searchResultContainer,
                        tag = "SearchResultFragment",
                        isNeedBackStack = true
                    ) {
                        SearchResultFragment()
                    }
                }
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