package com.nane.search.presentation.view

import androidx.fragment.app.activityViewModels
import com.nane.base.view.BaseBindFragment
import com.nane.search.R
import com.nane.search.databinding.SearchEmptyFragmentBinding
import com.nane.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchEmptyFragment : BaseBindFragment<SearchEmptyFragmentBinding, SearchViewModel>(R.layout.search_empty_fragment) {

    private val _viewModel: SearchViewModel by activityViewModels()

    override fun createViewModel(): SearchViewModel = _viewModel

    override fun initFragment(
        dataBinding: SearchEmptyFragmentBinding,
        viewModel: SearchViewModel
    ) {}
}