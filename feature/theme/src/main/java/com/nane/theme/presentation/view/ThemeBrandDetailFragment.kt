package com.nane.theme.presentation.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.theme.BR
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

        with(dataBinding.relatedItemsRecyclerView) {
            adapter ?: RelatedBrandPerfumesAdapter().apply {
                setOnItemClickListener(
                    object: RelatedBrandPerfumesAdapter.ItemClickListener {
                        override fun onItemClick(idx: Int) {

                        }
                    }
                )
                adapter = this
            }
            layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false).apply { layoutManager = this }
            if (itemDecorationCount == 0) addItemDecoration(RelatedBrandPerfumeItemDecoration())
        }

        viewModel.brandItem.observe(viewLifecycleOwner) {
            dataBinding.setVariable(BR.viewData, it)
        }

        viewModel.relatedPerfumes.observe(viewLifecycleOwner) {
            (dataBinding.relatedItemsRecyclerView.adapter as? RelatedBrandPerfumesAdapter)?.setItemList(it)
        }

        viewModel.getBrandDetailData(brandId, 4)
    }

    companion object {
        const val BRAND_ID = "brand_id"

        fun createArgument(brandId: Int): Bundle {
            return Bundle().apply {
                putInt(BRAND_ID, brandId)
            }
        }
    }
}