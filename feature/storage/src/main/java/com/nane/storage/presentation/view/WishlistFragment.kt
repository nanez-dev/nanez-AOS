package com.nane.storage.presentation.view

import com.nane.base.view.BaseBindFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nane.storage.R
import com.nane.storage.databinding.WishlistFragmentBinding
import com.nane.storage.presentation.data.StorageViewType
import com.nane.storage.presentation.view.adapter.WishListAdapter
import com.nane.storage.presentation.viewmodel.WishListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : BaseBindFragment<WishlistFragmentBinding, WishListViewModel>(R.layout.wishlist_fragment) {

    override fun createViewModel() = viewModels<WishListViewModel>().value

    override fun initFragment(dataBinding: WishlistFragmentBinding, viewModel: WishListViewModel) {
        dataBinding.apply {
            with(wishlistRecyclerView) {
                adapter ?: WishListAdapter().apply { adapter = this }
                layoutManager ?: GridLayoutManager(context, 2).apply { layoutManager = this }
            }
        }

        viewModel.wishList.observe(viewLifecycleOwner) {
            (dataBinding.wishlistRecyclerView.adapter as? WishListAdapter)?.setItemList(it)
        }

        viewModel.getMyList(type = StorageViewType.STORAGE_WISH_TYPE)
    }



}