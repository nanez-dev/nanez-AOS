package com.nane.storage.presentation.view

import com.nane.base.view.BaseBindFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nane.storage.R
import com.nane.storage.databinding.HavinglistFragmentBinding
import com.nane.storage.presentation.data.StorageViewType
import com.nane.storage.presentation.view.adapter.HavingListAdapter
import com.nane.storage.presentation.view.adapter.WishListAdapter
import com.nane.storage.presentation.viewmodel.HavingListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HavinglistFragment : BaseBindFragment<HavinglistFragmentBinding, HavingListViewModel>(R.layout.havinglist_fragment) {

    override fun createViewModel() = viewModels<HavingListViewModel>().value

    override fun initFragment(dataBinding: HavinglistFragmentBinding, viewModel: HavingListViewModel) {
        dataBinding.apply {
            with(havinglistRecyclerView) {
                adapter ?: HavingListAdapter().apply { adapter = this }
                layoutManager ?: GridLayoutManager(context, 2).apply { layoutManager = this }
            }
        }

        viewModel.havingList.observe(viewLifecycleOwner) {
            (dataBinding.havinglistRecyclerView.adapter as? HavingListAdapter)?.setItemList(it)
        }

        viewModel.getMyList(type = StorageViewType.STORAGE_HAVING_TYPE)
    }



}