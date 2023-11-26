package com.nane.theme.presentation.view

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.theme.R
import com.nane.theme.databinding.ThemeAccordDetailFragmentBinding
import com.nane.theme.presentation.view.adapter.RelatedAccordPerfumesAdapter
import com.nane.theme.presentation.view.adapter.decoration.RelatedAccordPerfumeItemDecoration
import com.nane.theme.presentation.viewmodel.ThemeAccordDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.NaneLog

@AndroidEntryPoint
class ThemeAccordDetailFragment : BaseBindFragment<ThemeAccordDetailFragmentBinding, ThemeAccordDetailViewModel>(R.layout.theme_accord_detail_fragment) {

    private var accordId: Int = 0

    override fun createViewModel(): ThemeAccordDetailViewModel = viewModels<ThemeAccordDetailViewModel>().value

    override fun initFragment(dataBinding: ThemeAccordDetailFragmentBinding, viewModel: ThemeAccordDetailViewModel) {
        accordId = arguments?.getInt(ACCORD_ID) ?: -1

        dataBinding.apply {
            with(actionBar) {
                setUseBackBtn {
                     parentFragmentManager
                         .beginTransaction()
                         .remove(this@ThemeAccordDetailFragment)
                         .commit()
                }
            }
        }

        with(dataBinding.relatedItemsRecyclerView) {
            adapter ?: RelatedAccordPerfumesAdapter().apply { adapter = this }
            layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false).apply { layoutManager = this }
            (adapter as RelatedAccordPerfumesAdapter).setOnItemClickListener(
                object: RelatedAccordPerfumesAdapter.ItemClickListener {
                    override fun onItemClick(idx: Int) {

                    }
                }
            )
            if (itemDecorationCount == 0) addItemDecoration(RelatedAccordPerfumeItemDecoration())
        }

        viewModel.accordDetailData.observe(viewLifecycleOwner) {
            dataBinding.viewData = it
            (dataBinding.relatedItemsRecyclerView.adapter as? RelatedAccordPerfumesAdapter)?.setItemList(it.relatedPerfumes)
        }

        viewModel.getAccordDetail(accordId)
    }

    companion object {
        const val ACCORD_ID = "accord_id"
    }
}