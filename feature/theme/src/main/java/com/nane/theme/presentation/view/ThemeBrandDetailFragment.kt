package com.nane.theme.presentation.view

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.theme.R
import com.nane.theme.databinding.ThemeBrandDetailFragmentBinding
import com.nane.theme.presentation.view.adapter.RelatedBrandPerfumesAdapter
import com.nane.theme.presentation.view.adapter.decoration.RelatedBrandPerfumeItemDecoration
import com.nane.theme.presentation.viewmodel.ThemeBrandDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeBrandDetailFragment : BaseBindFragment<ThemeBrandDetailFragmentBinding, ThemeBrandDetailViewModel>(R.layout.theme_brand_detail_fragment) {

    private var brandId: Int = 0

    override fun createViewModel(): ThemeBrandDetailViewModel = viewModels<ThemeBrandDetailViewModel>().value

    override fun initFragment(dataBinding: ThemeBrandDetailFragmentBinding, viewModel: ThemeBrandDetailViewModel) {
        brandId = arguments?.getInt(BRAND_ID) ?: -1

        dataBinding.apply {
            with(actionBar) {
                setUseBackBtn {
                     parentFragmentManager
                         .beginTransaction()
                         .remove(this@ThemeBrandDetailFragment)
                         .commit()
                }
            }
        }

        with(dataBinding.relatedItemsRecyclerView) {
            adapter ?: RelatedBrandPerfumesAdapter().apply { adapter = this }
            layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false).apply { layoutManager = this }
            (adapter as RelatedBrandPerfumesAdapter).setOnItemClickListener(
                object: RelatedBrandPerfumesAdapter.ItemClickListener {
                    override fun onItemClick(idx: Int) {

                    }
                }
            )
            if (itemDecorationCount == 0) addItemDecoration(RelatedBrandPerfumeItemDecoration())
        }

        viewModel.brandItem.observe(viewLifecycleOwner) {
            dataBinding.viewData = it
            (dataBinding.relatedItemsRecyclerView.adapter as? RelatedBrandPerfumesAdapter)?.setItemList(it.relatedPerfumes)
        }

        viewModel.getBrandDetailData(brandId, 4)
    }

    companion object {
        const val BRAND_ID = "brand_id"
    }
}