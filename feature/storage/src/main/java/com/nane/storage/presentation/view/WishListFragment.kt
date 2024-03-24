package com.nane.storage.presentation.view

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.detail.presentation.view.PerfumeDetailActivity
import com.nane.storage.R
import com.nane.storage.databinding.WishListFragmentBinding
import com.nane.storage.presentation.data.StorageViewType
import com.nane.storage.presentation.view.adapter.HavingListAdapter
import com.nane.storage.presentation.view.adapter.StorageItemDecoration
import com.nane.storage.presentation.view.adapter.WishListAdapter
import com.nane.storage.presentation.viewmodel.WishListViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.toDp

@AndroidEntryPoint
class WishListFragment : BaseBindFragment<WishListFragmentBinding, WishListViewModel>(R.layout.wish_list_fragment) {

    override fun createViewModel() = viewModels<WishListViewModel>().value

    override fun initFragment(dataBinding: WishListFragmentBinding, viewModel: WishListViewModel) {
        dataBinding.apply {
            with(recyclerView) {
                adapter ?: WishListAdapter().apply {
                    userActionListener = object : WishListAdapter.UserActionListener {
                        override fun onMoveDetail(perfumeId: Int) {
                            activity?.let {
                                it.startActivity(PerfumeDetailActivity.createIntent(it, perfumeId))
                            }
                        }
                    }
                    adapter = this
                }
                layoutManager ?: GridLayoutManager(context, 2).apply { layoutManager = this }

                if (itemDecorationCount == 0) {
                    addItemDecoration(StorageItemDecoration(spanCount = 2, spacing = 8.toDp(), horizontalMargin = 16.toDp()))
                }
            }
        }

        viewModel.wishList.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                dataBinding.recyclerView.visibility = View.GONE
                dataBinding.vgEmptyView.visibility = View.VISIBLE
            } else {
                (dataBinding.recyclerView.adapter as? WishListAdapter)?.setItemList(list)
                dataBinding.recyclerView.visibility = View.VISIBLE
                dataBinding.vgEmptyView.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getMyList(type = StorageViewType.STORAGE_WISH_TYPE)
    }
}