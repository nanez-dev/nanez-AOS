package com.nane.search.presentation.view

import android.view.View
import androidx.fragment.app.activityViewModels
import com.nane.base.view.BaseBindFragment
import com.nane.search.R
import com.nane.search.databinding.SearchNoResultFragmentBinding
import com.nane.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchNoResultFragment : BaseBindFragment<SearchNoResultFragmentBinding, SearchViewModel>(R.layout.search_no_result_fragment) {

    private val _viewModel: SearchViewModel by activityViewModels()

    override fun createViewModel(): SearchViewModel = _viewModel

    override fun initFragment(
        dataBinding: SearchNoResultFragmentBinding,
        viewModel: SearchViewModel
    ) {
        viewModel.initialized.observe(viewLifecycleOwner) {
            dataBinding.vgNoResult.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}