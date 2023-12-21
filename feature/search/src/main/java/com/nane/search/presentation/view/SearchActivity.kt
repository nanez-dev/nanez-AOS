package com.nane.search.presentation.view

import androidx.activity.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.search.R
import com.nane.search.databinding.SearchActivityBinding
import com.nane.search.presentation.viewmodel.SearchViewModel

class SearchActivity : BaseBindActivity<SearchActivityBinding, SearchViewModel>(R.layout.search_activity) {

    override fun createViewModel(): SearchViewModel = viewModels<SearchViewModel>().value

    override fun onActionBackPressed() {

    }

    override fun initActivity(dataBinding: SearchActivityBinding, viewModel: SearchViewModel) {

    }
}