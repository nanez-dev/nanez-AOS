package com.nane.theme.presentation.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.detail.presentation.view.PerfumeDetailActivity
import com.nane.theme.BR
import com.nane.theme.R
import com.nane.theme.databinding.ThemeAccordDetailFragmentBinding
import com.nane.theme.presentation.view.adapter.RelatedAccordPerfumesAdapter
import com.nane.theme.presentation.view.adapter.decoration.RelatedAccordPerfumeItemDecoration
import com.nane.theme.presentation.viewmodel.ThemeAccordDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeAccordDetailFragment : BaseBindFragment<ThemeAccordDetailFragmentBinding, ThemeAccordDetailViewModel>(R.layout.theme_accord_detail_fragment) {

    private var accordId: Int = 0

    override fun createViewModel(): ThemeAccordDetailViewModel = viewModels<ThemeAccordDetailViewModel>().value

    override fun initFragment(dataBinding: ThemeAccordDetailFragmentBinding, viewModel: ThemeAccordDetailViewModel) {
        accordId = arguments?.getInt(ACCORD_ID) ?: -1

        with(dataBinding.relatedItemsRecyclerView) {
            adapter ?: RelatedAccordPerfumesAdapter().apply {
                setOnItemClickListener(
                    object: RelatedAccordPerfumesAdapter.ItemClickListener {
                        override fun onItemClick(idx: Int) {
                            activity?.let {
                                it.startActivity(PerfumeDetailActivity.createIntent(it, idx))
                            }
                        }
                    }
                )
                adapter = this
            }
            layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false).apply { layoutManager = this }
            if (itemDecorationCount == 0) addItemDecoration(RelatedAccordPerfumeItemDecoration())
        }

        viewModel.accordItem.observe(viewLifecycleOwner) {
            dataBinding.setVariable(BR.viewData, it)
        }

        viewModel.relatedPerfume.observe(viewLifecycleOwner) {
            (dataBinding.relatedItemsRecyclerView.adapter as? RelatedAccordPerfumesAdapter)?.setItemList(it)
        }

        viewModel.getAccordDetail(accordId)
    }

    companion object {
        const val ACCORD_ID = "accord_id"

        fun createArgument(accordId: Int): Bundle {
            return Bundle().apply {
                putInt(ACCORD_ID, accordId)
            }
        }
    }
}