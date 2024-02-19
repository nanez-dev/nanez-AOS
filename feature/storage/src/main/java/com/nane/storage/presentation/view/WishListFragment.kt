package com.nane.storage.presentation.view

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.storage.R
import com.nane.storage.databinding.WishListFragmentBinding
import com.nane.storage.presentation.data.StorageViewType
import com.nane.storage.presentation.view.adapter.HavingListAdapter
import com.nane.storage.presentation.view.adapter.WishListAdapter
import com.nane.storage.presentation.viewmodel.WishListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : BaseBindFragment<WishListFragmentBinding, WishListViewModel>(R.layout.wish_list_fragment) {

    override fun createViewModel() = viewModels<WishListViewModel>().value

    override fun initFragment(dataBinding: WishListFragmentBinding, viewModel: WishListViewModel) {
        dataBinding.apply {
            with(recyclerView) {
                adapter ?: WishListAdapter().apply { adapter = this }
                layoutManager ?: GridLayoutManager(context, 2).apply { layoutManager = this }
            }
        }

        viewModel.wishList.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                dataBinding.recyclerView.visibility = View.GONE
                dataBinding.vgEmptyView.visibility = View.VISIBLE
            } else {
                (dataBinding.recyclerView.adapter as? HavingListAdapter)?.setItemList(list)
                dataBinding.recyclerView.visibility = View.VISIBLE
                dataBinding.vgEmptyView.visibility = View.GONE
            }
        }

        viewModel.getMyList(type = StorageViewType.STORAGE_WISH_TYPE)
    }

}