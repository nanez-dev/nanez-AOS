package com.nane.storage.presentation.view

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.storage.R
import com.nane.storage.databinding.HavingListFragmentBinding
import com.nane.storage.presentation.data.StorageViewType
import com.nane.storage.presentation.view.adapter.HavingListAdapter
import com.nane.storage.presentation.viewmodel.HavingListViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.session.SessionManager

@AndroidEntryPoint
class HavingListFragment : BaseBindFragment<HavingListFragmentBinding, HavingListViewModel>(R.layout.having_list_fragment) {

    override fun createViewModel() = viewModels<HavingListViewModel>().value

    override fun initFragment(dataBinding: HavingListFragmentBinding, viewModel: HavingListViewModel) {
        dataBinding.apply {
            with(recyclerView) {
                adapter ?: HavingListAdapter().apply { adapter = this }
                layoutManager ?: GridLayoutManager(context, 2).apply { layoutManager = this }
            }
        }

        viewModel.havingList.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                dataBinding.recyclerView.visibility = View.GONE
                dataBinding.vgEmptyView.visibility = View.VISIBLE
            } else {
                (dataBinding.recyclerView.adapter as? HavingListAdapter)?.setItemList(list)
                dataBinding.recyclerView.visibility = View.VISIBLE
                dataBinding.vgEmptyView.visibility = View.GONE
            }
        }

        viewModel.getMyList(type = StorageViewType.STORAGE_HAVING_TYPE)
    }



}